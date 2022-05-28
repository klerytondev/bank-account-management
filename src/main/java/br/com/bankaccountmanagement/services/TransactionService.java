package br.com.bankaccountmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.TransactionRepository;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;
import br.com.bankaccountmanagement.services.exceptions.NegocioException;
import br.com.bankaccountmanagement.services.exceptions.ObjetoNaoEncontradoException;

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
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));
		// Incrementa valor depositado no saldo existente
		accocuntModelOptional.get().setBalance(depositoDTO.getValue() + accocuntModelOptional.get().getBalance());
		TransactionModel transactionModelPersisit = new TransactionModel();
		// Cria registro da transação para a operação deposito
		transactionModelPersisit.setValue(depositoDTO.getValue());
		// Salva no banco uma nova transação
		transactionRepository.save(transactionModelPersisit);
		return transactionModelPersisit;
	}

	// Busca o saldo de uma account.
	@Transactional
	public TransactionModel balanceAccount(Long idAccount) {
		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));
		// Busca o saldo existente na account e armazena em uma variável
		Double balance = accocuntModelOptional.get().getBalance();
		TransactionModel transactionModelPersisit = new TransactionModel();
		// Cria registro da transação para a operação deposito
		transactionModelPersisit.setValue(balance);
		// Salva no banco uma nova transação
		transactionRepository.save(transactionModelPersisit);
		return transactionModelPersisit;
	}

	// Realiza o saque de dinheiro na conta.
	@Transactional
	public TransactionModel withdraw(WithdrawRequestDto withdrawRequestDto, Long idAccount) {
		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));
		if (accocuntModelOptional.get().getBalance() >= withdrawRequestDto.getValue()) {
			accocuntModelOptional.get()
					.setBalance(accocuntModelOptional.get().getBalance() - withdrawRequestDto.getValue());
			TransactionModel transactionModelPersisit = new TransactionModel();
			// Cria registro da transação para a operação deposito
			transactionModelPersisit.setValue(withdrawRequestDto.getValue());
			// Salva no banco uma nova transação
			transactionRepository.save(transactionModelPersisit);
			return transactionModelPersisit;
		}
		// Exceção para caso não haja saldo suficiente para saque na account
		throw new NegocioException(String.format(
				"The account does not have enough balance for this operation! Balance: {0}, amount to be debited {1}",
				accocuntModelOptional.get().getBalance(), withdrawRequestDto.getValue()));
	}

	// Retorna todas as transações de uma account
	@Transactional
	public List<TransactionModel> findAll(Long idAccount) {

		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));
		/*
		 * Verifica se existe transações salvas no banco de acordo com o is da account
		 * passado, caso contrario retorna exception.
		 */

		if (accocuntModelOptional.get().getTransactionModels().isEmpty()) {
			throw new ObjetoNaoEncontradoException("transactions not found!");
		}
		// Salva transactions existentes no banco de dados em uma lista de transactions
		List<TransactionModel> transactionModelsList = new ArrayList<>();
		for (TransactionModel transactionModel : accocuntModelOptional.get().getTransactionModels()) {
			transactionModelsList.add(transactionModel);
		}
		return transactionModelsList;
	}

}