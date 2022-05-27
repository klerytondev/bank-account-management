package br.com.bankaccountmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bankaccountmanagement.models.AccountModel;

/*
 * Esta classe cria um beans para persisti no banco
 * 
 * */

public interface AccountRepository  extends JpaRepository<AccountModel, Long>{

}
