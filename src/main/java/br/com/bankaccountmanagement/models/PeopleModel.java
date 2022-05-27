package br.com.bankaccountmanagement.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_PEOPLE")
public class PeopleModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPeople;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String cpf;

	public PeopleModel() {
	}

	public PeopleModel(Long idPeople, String name, String cpf) {
		super();
		this.idPeople = idPeople;
		this.name = name;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getIdPeople() {
		return idPeople;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, idPeople, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeopleModel other = (PeopleModel) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(idPeople, other.idPeople)
				&& Objects.equals(name, other.name);
	}

}
