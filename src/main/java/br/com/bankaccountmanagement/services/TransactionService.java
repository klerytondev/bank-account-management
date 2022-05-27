package br.com.bankaccountmanagement.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.TransactionRepository;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	public TransactionModel depositAccount(DepositRequestDto depositoDTO, Long idAccount) {
		// Verifica se a account existe no banco
		Optional<AccountModel> accocuntModelOptional = accountRepository.findById(idAccount);
		accocuntModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));
		//Incrementa valor depositado no saldo existente
		accocuntModelOptional.get().setBalance(depositoDTO.getValue() + accocuntModelOptional.get().getBalance());
		TransactionModel transactionModelPersisit = new TransactionModel();
		//Cria registro da transação para a operação deposito
		transactionModelPersisit.setValue(accocuntModelOptional.get().getBalance());
		return transactionModelPersisit;
	}

}
