package br.com.bankaccountmanagement.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;
import br.com.bankaccountmanagement.services.TransactionService;
import br.com.bankaccountmanagement.utils.Utils;
import io.restassured.http.ContentType;

@DisplayName("Testes da controller de transactions")
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
	
	@Autowired
	private TransactionController transactionController;
	
	@MockBean
	TransactionService transactionService;
	
	@BeforeEach
	public void setup(){
		standaloneSetup(this.transactionController);
	}
	
	TransactionModel transactionModel = Utils.createTransactionModel(1L, 20000.00);
	DepositRequestDto depositRequestDto = Utils.createDepositRequestDto(AccountType.CORRENTE, 2000.0);
	WithdrawRequestDto withdrawRequestDto = Utils.createWithdrawRequestDto(AccountType.CORRENTE, 100.00);
	
	@DisplayName("Realiza um deposito com sucesso")
	@Test
	public void shouldDepositSuccess() {
		
		
		when(this.transactionService.depositAccount(depositRequestDto, 1L))
			.thenReturn(Utils.createTransactionModel(1L, 20000.00));
		given()
			.contentType(ContentType.JSON)
			.body(depositRequestDto)
		.when()
			.post("/v1/bankAccountManagement/depositar/{idAccount}", 1L)
		.then()
			.statusCode(201)
			.extract().response();
	}
	
	@DisplayName("Falha ao tentar realizar um deposit em uma account com body vazio")
	@Test
	public void shouldDepositFail() {
		
		given()
			.contentType(ContentType.JSON)
			.body("")
		.when()
			.post("/v1/bankAccountManagement/depositar/{idAccount}", 1L)
		.then()
			.statusCode(400)
			.extract().response();
	}
	
	@DisplayName("Realiza operação de busca de saldo em uma account com sucesso")
	@Test
	public void searchBalanceSuccessfully() {
		
		when(this.transactionService.balanceAccount(1L))
			.thenReturn(2000.0);
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("/v1/bankAccountManagement/saldo/{idAccount}", 1L)
		.then()
			.statusCode(201)
			.extract().response();
	}
	
	@DisplayName("Realiza operação de busca de saldo com erro ao receber id da account")
	@Test
	public void searchBalanceFail() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("/v1/bankAccountManagement/saldo/{idAccount}", "")
		.then()
			.statusCode(404)
			.extract().response();
	}
	
	@DisplayName("Realiza um saque com sucesso")
	@Test
	public void shouldwithdrawSuccess() {
		
		
		when(this.transactionService.withdraw(withdrawRequestDto, 1L))
			.thenReturn(Utils.createTransactionModel(1L, 100.00));
		given()
			.contentType(ContentType.JSON)
			.body(withdrawRequestDto)
		.when()
			.post("/v1/bankAccountManagement/sacar/{idAccount}", 1L)
		.then()
			.statusCode(201)
			.extract().response();
	}
	
	@DisplayName("Realiza um saque com error")
	@Test
	public void shouldwithdrawFail() {
		
		when(this.transactionService.withdraw(withdrawRequestDto, 1L))
			.thenReturn(Utils.createTransactionModel(1L, 100.00));
		given()
			.contentType(ContentType.JSON)
			.body("")
		.when()
			.post("/v1/bankAccountManagement/sacar/{idAccount}", 1L)
		.then()
			.statusCode(400)
			.extract().response();
	}
}
