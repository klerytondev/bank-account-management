package br.com.bankaccountmanagement.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bankaccountmanagement.requestDto.PeopleRequestDto;
import br.com.bankaccountmanagement.services.PeopleService;
import br.com.bankaccountmanagement.utils.Utils;
import io.restassured.http.ContentType;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {
	
	@Autowired
	private PeopleController peopleController;
	
	@MockBean
	private PeopleService peopleServiceMock;
	
	@BeforeEach
	public void setup(){
		standaloneSetup(this.peopleController);
	}
	
	@Test
	public void shouldCreatePeople() {
		
		PeopleRequestDto peopleRequestDto = Utils.createPeopleRequestDto("kleryton", "07869696447");
		
		when(this.peopleServiceMock.createPeople(peopleRequestDto))
			.thenReturn(Utils.createPeople("kleryton", "07869696447"));
		given()
			.contentType(ContentType.JSON)
			.body(peopleRequestDto)
		.when()
			.post("/v1/bankAccountManagement/add/people")
		.then()
			.statusCode(201)
			.extract().response();
	}
}
