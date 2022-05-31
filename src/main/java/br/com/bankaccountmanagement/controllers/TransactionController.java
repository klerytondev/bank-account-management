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
import br.com.bankaccountmanagement.requestDto.ExtractByPeriodRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;
import br.com.bankaccountmanagement.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * @author: Kleryton de souza
 */
@RestController
@RequestMapping(value = "/v1/bankAccountManagement")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Operation(summary = "Realiza o depósito de dinheiro em uma account.")
	@PostMapping("/depositar/{idAccount}")
	public ResponseEntity<Object> deposit(@Parameter @Valid @RequestBody DepositRequestDto depositoDTO,
			@PathVariable Long idAccount) {
		TransactionModel transactionModel = transactionService.depositAccount(depositoDTO, idAccount);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saldo atualizado! " + transactionModel);
	}

	@Operation(summary = "Busca o saldo de uma account.")
	@GetMapping("/saldo/{idAccount}")
	public ResponseEntity<Object> balanceAccount(@Parameter  @PathVariable Long idAccount) {
		Double balance = transactionService.balanceAccount(idAccount);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saldo: R$ " + balance);
	}

	@Operation(summary = "Realiza o saque de dinheiro na conta.")
	@PostMapping("/sacar/{idAccount}")
	public ResponseEntity<Object> withdraw(@Parameter  @Valid @RequestBody WithdrawRequestDto withdrawRequestDto,
			@PathVariable Long idAccount) {
		TransactionModel transactionModel = transactionService.withdraw(withdrawRequestDto, idAccount);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saldo atualizado! " + transactionModel);
	}

	@Operation(summary = "Retorna todas as transações de uma account")
	@GetMapping("/transacoes/{idAccount}")
	public ResponseEntity<Object> getAllTransactions(@Parameter @PathVariable Long idAccount) {
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransactions(idAccount));

	}

	@Operation(summary = "Retorna as transações de auma account por periodo")
	@GetMapping("/transacoes/periodo/{idAccount}")
	public ResponseEntity<Object> getAllPeriodTransactions(
			@Valid @RequestBody ExtractByPeriodRequestDto extractByPeriodRequestDto,
			@Parameter @PathVariable Long idAccount) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(transactionService.getAllPeriodTransactions(extractByPeriodRequestDto, idAccount));

	}

}
