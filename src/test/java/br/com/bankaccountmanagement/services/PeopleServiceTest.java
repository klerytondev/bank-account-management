package br.com.bankaccountmanagement.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.repositories.PeopleRepository;
import br.com.bankaccountmanagement.requestDto.PeopleRequestDto;
import br.com.bankaccountmanagement.utils.DateUtils;
import br.com.bankaccountmanagement.utils.Utils;

@DisplayName("Testes da service de People")
public class PeopleServiceTest {

	@InjectMocks
	PeopleService peopleService;

	@Mock
	private PeopleModel peopleModel;

	@Mock
	private PeopleRepository peopleRepositoryMock;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Realiza a criação de uma people")
	void shouldCreatePeople() {
		// Cenário
		// Cria uma pessoa
		PeopleModel peopleModel = Utils.createPeople("kleryton", "07869696447");
		// Cria o DTO da requisição de criar people
		PeopleRequestDto peopleRequestDto = Utils.createPeopleRequestDto("kleryton", "07869696447");

		// Ação
		// Recupera a people com Mock do peopleRepository
		Mockito.when(peopleRepositoryMock.existsByCpf("07869696447")).thenReturn(false);
		// Salva a account com mock do accountRepositoryMock
		Mockito.when(peopleRepositoryMock.save(ArgumentMatchers.any())).thenReturn(peopleModel);
		// Executa o metodo de criar account do accountService
		PeopleModel peopleReturned = peopleService.createPeople(peopleRequestDto);

		// Validações
		// Verifica se a account é notnull
		Assertions.assertNotNull(peopleReturned);
		Assertions.assertEquals(peopleRequestDto.getCpf(), peopleReturned.getCpf());
		Assertions.assertEquals(peopleRequestDto.getBirthDate(),
				DateUtils.convertLocalDateToString(peopleReturned.getBirthDate()));
		Assertions.assertEquals(peopleRequestDto.getName(), peopleReturned.getName());
	}

	@Test
	@DisplayName("Error ao criar people por CPF existente")
	void shouldFailPeopleByCpfDuplicate() {
		// Cenário
		// Cria o DTO da requisição de criar people
		PeopleRequestDto peopleRequestDto = Utils.createPeopleRequestDto("kleryton", "07869696447");

		// Recupera a people com Mock do peopleRepository
		Mockito.when(peopleRepositoryMock.existsByCpf("07869696447")).thenReturn(true);

		// Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			peopleService.createPeople(peopleRequestDto);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("CPF is already in use!", e.getMessage());
		}
	}

}
