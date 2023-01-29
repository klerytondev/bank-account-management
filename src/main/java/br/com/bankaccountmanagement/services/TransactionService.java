package br.com.bankaccountmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.TransactionRepository;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.requestDto.ExtractByPeriodRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;
import br.com.bankaccountmanagement.services.exceptions.IntegridadeDeDadosException;
import br.com.bankaccountmanagement.services.exceptions.ObjetoNaoEncontradoException;
import br.com.bankaccountmanagement.utils.DateUtils;

/**
 * @author: Kleryton de souza
 */
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	// Realiza o depósito de dinheiro em uma account.
	@Transactional
	public TransactionModel depositAccount(DepositRequestDto depositoDTO, Long idAccount) {
		// Verifica se a account existe no banco
		// TODO verificar porque está lançando status 500
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));

		// Verifica se a account está bloqueada no banco. Caso afirmativo lança uma
		// exception.
		if (accocuntModelOptional.get().getActiveFlag().equals(ActiveFlag.BLOCK)) {
			throw new IntegridadeDeDadosException("The account is blocked, it is not possible to make a deposit.");
		}
		// Incrementa valor depositado no saldo existente
		AccountModel accountModel = accocuntModelOptional.get();
		accountModel.setBalance(depositoDTO.getValue() + accountModel.getBalance());
		TransactionModel transactionModelPersisit = new TransactionModel();

		// Cria registro da transação para a operação deposito
		transactionModelPersisit.setValue(depositoDTO.getValue());
		transactionModelPersisit.setIdAccount(idAccount);

		// Salva no banco uma nova transação
		TransactionModel saved = transactionRepository.save(transactionModelPersisit);
		return saved;
	}

	// Busca o saldo de uma account.
	@Transactional
	public Double balanceAccount(Long idAccount) {
		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));
		// Busca o saldo existente na account e armazena em uma variável
		Double balance = accocuntModelOptional.get().getBalance();
		return balance;
	}

	// Realiza o saque de dinheiro na conta.
	@Transactional
	public TransactionModel withdraw(WithdrawRequestDto withdrawRequestDto, Long idAccount) {
		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));

		// Verifica se a account está bloqueada no banco. Caso afirmativo lança uma
		// exception.
		if (accocuntModelOptional.get().getActiveFlag().equals(ActiveFlag.BLOCK)) {
			throw new IntegridadeDeDadosException("The account is blocked, it is not possible to make a withdrawal.");
		}
		// Verifico se ha limite suficiente para saque
		if (withdrawRequestDto.getValue() > accocuntModelOptional.get().getWithdrawalLimit()) {
			throw new IntegridadeDeDadosException("Insufficient limit for withdrawal.");
		}
		// Verifica se a acoount possui saldo suficiente para o saque
		if (withdrawRequestDto.getValue() <= accocuntModelOptional.get().getBalance()) {
			accocuntModelOptional.get()
					.setBalance(accocuntModelOptional.get().getBalance() - withdrawRequestDto.getValue());
			// Atualizo o limite
			accocuntModelOptional.get().setWithdrawalLimit(
					accocuntModelOptional.get().getWithdrawalLimit() - withdrawRequestDto.getValue());
			TransactionModel transactionModelPersisit = new TransactionModel();

			// Cria registro da transação para a operação deposito
			transactionModelPersisit.setValue(withdrawRequestDto.getValue());
			transactionModelPersisit.setIdAccount(idAccount);

			// Salva no banco uma nova transação
			TransactionModel saved = transactionRepository.save(transactionModelPersisit);
			return saved;

		} else {
			// Exceção para caso não haja saldo suficiente para saque na account
			throw new IntegridadeDeDadosException("Insufficient balance for withdrawal.");
		}

	}

	// Retorna todas as transações de uma account
	@Transactional
	public List<TransactionModel> getAllTransactions(Long idAccount) {

		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));

		// Verifica se existe transações salvas no banco de acordo com o id da account
		// passado, caso contrario retorna exception.
		if (accocuntModelOptional.get().getTransactionModels() == null
				|| accocuntModelOptional.get().getTransactionModels().isEmpty()) {
			throw new ObjetoNaoEncontradoException("Transactions not found!");
		}
		// Recupera transactions existentes no banco de dados e retorna em uma lista de
		// transactions
		List<TransactionModel> transactionModelsList = new ArrayList<>();
		for (TransactionModel transactionModel : accocuntModelOptional.get().getTransactionModels()) {
			transactionModelsList.add(transactionModel);
		}
		return transactionModelsList;
	}

	// Retorna todas as transações de uma account por periodo
	@Transactional
	public List<TransactionModel> getAllPeriodTransactions(ExtractByPeriodRequestDto extractByPeriodRequestDto,
			Long idAccount) {

		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));

		// Verifica se existe transações salvas no banco de acordo com o is da account
		// passado, caso contrario retorna exception.
		if (accocuntModelOptional.get().getTransactionModels() == null
				|| accocuntModelOptional.get().getTransactionModels().isEmpty()) {
			throw new ObjetoNaoEncontradoException("Transactions not found!");
		}
		// Salva transactions existentes no banco de dados em uma lista de transactions
		// de acordo com o periodo passado
		List<TransactionModel> transactionModelsList = new ArrayList<>();
		for (TransactionModel transactionModel : accocuntModelOptional.get().getTransactionModels()) {
			if ((transactionModel.getTransactionDate().isAfter(
					DateUtils.convertStringToLocalDate(extractByPeriodRequestDto.getInitialDate()).minusDays(1))
					&& transactionModel.getTransactionDate().isBefore(DateUtils
							.convertStringToLocalDate(extractByPeriodRequestDto.getFinalDate()).plusDays(1)))) {
				transactionModelsList.add(transactionModel);
			}
		}
		return transactionModelsList;
	}
}
