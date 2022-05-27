package br.com.bankaccountmanagement.controllers;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.services.AccountService;
import br.com.gleisonandrade.bancoapi.dto.DepositoDTO;
import br.com.gleisonandrade.bancoapi.util.ContaExtrato;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/bankAccountManagement")
@Api(value = "Bank Account Management - API Restfull")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class AccountController {
	
	@Autowired
	AccountService accountService;

	// SaveAccount
	@ApiOperation(value = "Cadastra uma nova conta no sistema e pessoa associada a ela.")
	@PostMapping("/account/add")
	public ResponseEntity<Object> saveAccount(@RequestBody @Valid AccountRequestDto accountRequestDto, @PathVariable Long idPeople) {
		PeopleModel peopleModel = accountService.createAccount(accountRequestDto, idPeople);
		return ResponseEntity.status(HttpStatus.CREATED).body(peopleModel);
	}
	
	@ApiOperation(value = "Realiza o depósito de dinheiro em uma conta.")
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Conta inexistente, banco ou agência. "),
			@ApiResponse(code = 422, message = "Ocorreu um erro de validação. ")
	})
	@PostMapping("/depositar")
	public ResponseEntity<Void> depositar(@ApiParam(value = "Dados necessários para realizar o depósito de dinheiro de uma conta") @Valid @RequestBody DepositoDTO depositoDTO) {
		ContaExtrato extrato = contaService.depositar(depositoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}/extrato/{extratoId}").build()
				.expand(extrato.getContaId(), extrato.getExtratoId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
