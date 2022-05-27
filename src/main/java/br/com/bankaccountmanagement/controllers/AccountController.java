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

import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/bankAccountManagement")
@Api(value = "Bank Account Management - API Restfull")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class AccountController {
	
	@Autowired
	AccountService accountService;

	// Create account e salva em uma people
	@ApiOperation(value = "Cadastra uma nova conta no sistema e pessoa associada a ela.")
	@PostMapping("/add/{idPeople}")
	public ResponseEntity<Object> saveAccount(@RequestBody @Valid AccountRequestDto accountRequestDto,
			@PathVariable Long idPeople) {
		PeopleModel peopleModel = accountService.createAccount(accountRequestDto, idPeople);
		return ResponseEntity.status(HttpStatus.CREATED).body(peopleModel);
	}
	

}
