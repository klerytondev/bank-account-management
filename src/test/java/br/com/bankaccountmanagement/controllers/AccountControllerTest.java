package br.com.bankaccountmanagement.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.requestDto.AccountActiveFlagRequestDto;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.services.AccountService;
import br.com.bankaccountmanagement.utils.Utils;
import io.restassured.http.ContentType;

@DisplayName("Testes da controller de account")
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	
	@Autowired
	private AccountController accountController;
	
	@MockBean
	AccountService accountServiceMock;
	
	
	AccountModel accountModel;
	
	@BeforeEach
	public void setup(){
		standaloneSetup(this.accountController);
		MockitoAnnotations.openMocks(this);
		accountModel = Utils.createAccountModelBlocked(0.0, 20000.00);
	}
	
	AccountRequestDto accountRequestDto = Utils.createAccountRequestDto();
	AccountActiveFlagRequestDto accountActiveFlagRequestDto = Utils.cretaeAccountActiveRequestDto();
	
	
	@DisplayName("Realiza a criação de uma account")
	@Test
	public void shouldCreateAccount() {
		
		
		when(this.accountServiceMock.createAccount(accountRequestDto, 1L))
			.thenReturn(Utils.createAccountModelActive(0.0, 20000.00));
		given()
			.contentType(ContentType.JSON)
			.body(accountRequestDto)
		.when()
			.post("/v1/bankAccountManagement/add/account/{idPeople}", 1L)
		.then()
			.statusCode(201)
			.extract().response();
	}
	
	@DisplayName("Falha ao tentar criar uma account com body vazio")
	@Test
	public void shouldCreateAccountFail() {
		
		given()
			.contentType(ContentType.JSON)
			.body("")
		.when()
			.post("/v1/bankAccountManagement/add/account/{idPeople}", 1L)
		.then()
			.statusCode(400)
			.extract().response();
	}

//	@DisplayName("Altera o status de uma account")
//	@Test
//	public void shouldChangeAccountStatus() {
//		
//		when(this.accountServiceMock.activeFlag(accountActiveFlagRequestDto, 1L))
//			.thenReturn(accountModel);
//		given()
//			.contentType(ContentType.JSON)
//			.body(accountActiveFlagRequestDto)
//		.when()
//			.post("/v1/bankAccountManagement/status/{idAccount}", 1L)
//		.then()
//			.statusCode(201)
//			.extract().response();
//	}
	
	@DisplayName("Falha ao tentar alterar status de uma account")
	@Test
	public void shouldChangeAccountStatusFail() {
		
		given()
			.contentType(ContentType.JSON)
			.body("")
		.when()
			.post("/v1/bankAccountManagement/status/{idAccount}", 1L)
		.then()
			.statusCode(400)
			.extract().response();
	}
}
