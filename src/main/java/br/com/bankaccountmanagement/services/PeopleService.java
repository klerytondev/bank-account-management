package br.com.bankaccountmanagement.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.repositories.PeopleRepository;
import br.com.bankaccountmanagement.requestDto.PeopleRequestDto;
import br.com.bankaccountmanagement.services.exceptions.ConflictDeDadosException;
import br.com.bankaccountmanagement.utils.DateUtils;

/**
 * @author: Kleryton de souza
 */
@Service
public class PeopleService {

	@Autowired
	PeopleRepository peopleRepository;

	@Transactional
	// Create account e salva em uma people
	public PeopleModel createPeople(PeopleRequestDto peopleRequestDto) {

		// Verifica se a people já existe no banco
		if (peopleRepository.existsByCpf(peopleRequestDto.getCpf()))
			throw new ConflictDeDadosException("CPF is already in use!");

		// Converte o peoplerequestDto em um peopleModel
		PeopleModel peopleModelPersist = new PeopleModel();
		peopleModelPersist = convertDtoToModel(peopleRequestDto);

		// Salva uma nova pessoa no banco de dados
		return peopleRepository.save(peopleModelPersist);

	}

	// Coverte um PeopleRequestDto em um PeopleModel
	public PeopleModel convertDtoToModel(PeopleRequestDto peopleRequestDto) {

		PeopleModel peopleModel = new PeopleModel();
		peopleModel.setName(peopleRequestDto.getName());
		peopleModel.setCpf(peopleRequestDto.getCpf());
		peopleModel.setBirthDate(DateUtils.convertStringToLocalDate(peopleRequestDto.getBirthDate()));
		return peopleModel;
	}

}
