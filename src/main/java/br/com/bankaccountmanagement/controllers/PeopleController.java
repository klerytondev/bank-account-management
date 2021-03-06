package br.com.bankaccountmanagement.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.requestDto.PeoplerequestDto;
import br.com.bankaccountmanagement.services.PeopleService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @author: Kleryton de souza
 */
@RestController
@RequestMapping(value = "/v1/bankAccountManagement")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class PeopleController {

	@Autowired
	PeopleService peopleService;

	@Operation(summary = "Cria uma nova pessoa.")
	@PostMapping("/add/people")
	public ResponseEntity<Object> createPeople(@RequestBody @Valid PeoplerequestDto peoplerequestDto) {
		PeopleModel peopleModel = peopleService.createPeople(peoplerequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(peopleModel);
	}
}
