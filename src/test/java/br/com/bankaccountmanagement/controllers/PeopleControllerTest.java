package br.com.bankaccountmanagement.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bankaccountmanagement.requestDto.PeoplerequestDto;
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
	
	PeoplerequestDto peoplerequestDto = Utils.createPeopleRequestDto("kleryton", "07869696447");
	
	@Test
	public void shouldCreatePeople() {
		
		
		when(this.peopleServiceMock.createPeople(peoplerequestDto))
			.thenReturn(Utils.createPeople("kleryton", "07869696447"));
		given()
			.contentType(ContentType.JSON)
			.body(peoplerequestDto)
		.when()
			.post("/v1/bankAccountManagement/add/people")
		.then()
			.statusCode(201)
			.extract().response();
	}
}
