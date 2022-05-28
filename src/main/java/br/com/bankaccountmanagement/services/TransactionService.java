package br.com.bankaccountmanagement.services;

import java.util.Optional;

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
	private TransactionModel withdraw(WithdrawRequestDto withdrawRequestDto, Long idAccount) {
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
		throw new NegocioException(String.format(
				"A conta não possuí saldo suficiente para essa operação! Saldo: {0}, valor a ser debitado {1}",
				accocuntModelOptional.get().getBalance(), withdrawRequestDto.getValue()));
	}

}
