package br.com.bankaccountmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bankaccountmanagement.models.PeopleModel;

/*
 * Esta classe cria um beans para persisti no banco
 * 
 * */

public interface PeopleRepository extends JpaRepository<PeopleModel, Long> {

}
