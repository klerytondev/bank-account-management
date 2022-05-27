package br.com.bankaccountmanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_TRANSACTION")
public class TransactionModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	

}
