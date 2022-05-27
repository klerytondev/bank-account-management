package br.com.bankaccountmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bankaccountmanagement.models.TransactionModel;

/*
 * Esta classe cria um beans para persisti no banco
 * 
 * */

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

}
