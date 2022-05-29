package br.com.bankaccountmanagement.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.PeopleRepository;
import br.com.bankaccountmanagement.requestDto.AccountActiveFlagRequestDto;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.services.exceptions.ConflictDeDadosException;
import br.com.bankaccountmanagement.services.exceptions.ObjetoNaoEncontradoException;

/**
 * @author: Kleryton de souza
 */
@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PeopleRepository peopleRepository;

	@Transactional
	// Cria account e salva em uma people
	public PeopleModel createAccount(AccountRequestDto accountRequestDto, Long idPeople) {

		// Verifica se a people existe no banco
		Optional<PeopleModel> peopleModelOptional = peopleRepository.findById(idPeople);
		peopleModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("People not found."));

		// Cria uma nova account e converte o accountRequestDto em um accountModel
		AccountModel accountModelPersisti = new AccountModel();
		accountModelPersisti = convertDtoToModel(accountRequestDto);

		// Seta uma account em people
		peopleModelOptional.get().setAccountModels(accountModelPersisti);

		PeopleModel peopleModelPersisti;

		// lança uma exceção se a account já existir na people
		try {
			peopleModelPersisti = peopleRepository.save(peopleModelOptional.get());
		} catch (DataIntegrityViolationException e) {
			throw new ConflictDeDadosException("Account is already in use!");
		}
		return peopleModelPersisti;
	}

	@Transactional
	// Altera o status de uma account.
	public AccountModel activeFlag(AccountActiveFlagRequestDto accountActiveFlagRequestDto, Long idAccount) {
		// Verifica se a people existe no banco
		Optional<AccountModel> accountModelOptional = accountRepository.findById(idAccount);
		accountModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));

		if (accountActiveFlagRequestDto.getAccountStatus().equalsIgnoreCase("BLOCK")) {
			accountModelOptional.get().setActiveFlag(ActiveFlag.BLOCK);
		} else if ((accountActiveFlagRequestDto.getAccountStatus().equalsIgnoreCase("ACTIVE"))) {
			accountModelOptional.get().setActiveFlag(ActiveFlag.ACTIVE);
		}
		AccountModel accountModel = accountRepository.save(accountModelOptional.get());
		return accountModel;
	}
	/*
	 * Este metodo coverte um requestDTO em accountModel
	 */
	public AccountModel convertDtoToModel(AccountRequestDto accountRequestDto) {

		AccountModel accountModel = new AccountModel();
		accountModel.setWithdrawalLimit(accountRequestDto.getWithdrawalLimit());
		accountModel.setAccountType(accountRequestDto.getAccountType());

		return accountModel;
	}

}
