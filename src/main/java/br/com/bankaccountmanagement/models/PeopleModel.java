package br.com.bankaccountmanagement.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author: Kleryton de souza
 */

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

	@Column(nullable = false)
	private String cpf;

	//Uma pessoa pode ter várias contas @oneToMany
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "people_id", referencedColumnName = "idPeople", foreignKey = @ForeignKey(name = "fk_accounts"))
	private Set<AccountModel> accountModels;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	public PeopleModel() {
	}

	public PeopleModel(Long idPeople, String name, String cpf, Set<AccountModel> accountModels, Date birthDate) {
		super();
		this.idPeople = idPeople;
		this.name = name;
		this.cpf = cpf;
		this.accountModels = accountModels;
		this.birthDate = birthDate;
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

	public Set<AccountModel> getAccountModels() {
		return accountModels;
	}

	public void setAccountModels(AccountModel accountModel) {
		this.accountModels.add(accountModel);
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getIdPeople() {
		return idPeople;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountModels, birthDate, cpf, idPeople, name);
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
		return Objects.equals(accountModels, other.accountModels) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(idPeople, other.idPeople)
				&& Objects.equals(name, other.name);
	}

}
