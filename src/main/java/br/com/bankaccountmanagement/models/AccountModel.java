package br.com.bankaccountmanagement.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.kleryton.bankingsystem.models.enums.CardFlag;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_ACCOUNT")
public class AccountModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double balance;
	
	private Double withdrawalLimit;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CardFlag flag;
	

}
