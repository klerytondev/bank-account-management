package br.com.bankaccountmanagement.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.services.exceptions.ConflictDeDadosException;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Transactional
	// Create account
	public AccountModel createAccount(AccountRequestDto accountRequestDto) {

		AccountModel accountModel = convertDtoToModel(accountRequestDto);

		// Exceção para verificar de se o CPF já está em uso no banco
		try {
			accountRepository.save(accountModel);
		} catch (DataIntegrityViolationException e) {
			throw new ConflictDeDadosException("CPF is already in use!");
		}
		return accountModel;
	}

	/*
	 * Este metodo coverte um requestDTO em accountModel
	 */
	public AccountModel convertDtoToModel(AccountRequestDto accountRequestDto) {

		AccountModel accountModel = new AccountModel();
		accountModel.setBalance(accountRequestDto.getBalance());
		accountModel.setWithdrawalLimit(accountRequestDto.getWithdrawalLimit());
		accountModel.setAccountType(accountRequestDto.getAccountType());

		return accountModel;
	}

}
