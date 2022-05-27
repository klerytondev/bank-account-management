package br.com.bankaccountmanagement.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/v1/bankAccountManagement")
@Api(value = "Bank Account Management - API Restfull")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@ApiOperation(value = "Realiza o depósito de dinheiro em uma account.")
	@PostMapping("/depositar/{idAccount}")
	public ResponseEntity<Object> deposit(@ApiParam @Valid @RequestBody DepositRequestDto depositoDTO,
			@PathVariable Long idAccount) {
		TransactionModel transactionModel = transactionService.depositAccount(depositoDTO, idAccount);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saldo atualizado! " + transactionModel);
	}

	@ApiOperation(value = "Busca o saldo de uma account.")
	@GetMapping("/saldo/{idAccount}")
	public ResponseEntity<Object> extrato(@ApiParam @PathVariable Long idAccount) {
		TransactionModel transactionModel = transactionService.balanceAccount(idAccount);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saldo: " + transactionModel);
	}
}
