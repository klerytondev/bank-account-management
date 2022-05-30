package br.com.bankaccountmanagement.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.requestDto.AccountActiveFlagRequestDto;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author: Kleryton de souza
 */
@RestController
@RequestMapping(value = "/v1/bankAccountManagement")
@Api(value = "Bank Account Management - API Restfull")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	AccountService accountService;

	@ApiOperation(value = "Cadastra uma nova conta no sistema e pessoa associada a ela.")
	@PostMapping("/add/account/{idPeople}")
	public ResponseEntity<Object> saveAccount(@RequestBody @Valid AccountRequestDto accountRequestDto,
			@PathVariable Long idPeople) {
		AccountModel accountModel = accountService.createAccount(accountRequestDto, idPeople);
		return ResponseEntity.status(HttpStatus.CREATED).body(accountModel);
	}

	@ApiOperation(value = "Altera o status de uma account.")
	@PostMapping("/status/{idAccount}")
	public ResponseEntity<Object> activeFlag(
			@RequestBody @Valid AccountActiveFlagRequestDto accountActiveFlagRequestDto, @PathVariable Long idAccount) {
		accountService.activeFlag(accountActiveFlagRequestDto, idAccount);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Status da account alterado para: " + accountActiveFlagRequestDto.getAccountStatus());
	}

}
