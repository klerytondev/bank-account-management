package br.com.bankaccountmanagement.requestDto;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.bankaccountmanagement.models.AccountModel;

/*
 * Esta classe Transferi e manipula atributos recebidos por parametro da account via controller
 * */

public class PeoplerequestDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor

	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Length(max = 50, message = "{campo.nome.caracteres}")
	private String name;

	@CPF(message = "{campo.accountcpf.nulo}")
	private String cpf;

	private Set<AccountModel> accountModels;

	@NotEmpty(message = "{campo.data.obrigatorio}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	public PeoplerequestDto() {
	}

	public PeoplerequestDto(
			@NotEmpty(message = "{campo.nome.obrigatorio}") @Length(max = 50, message = "{campo.nome.caracteres}") String name,
			@CPF(message = "{campo.accountcpf.nulo}") String cpf, Set<AccountModel> accountModels,
			@NotEmpty(message = "{campo.data.obrigatorio}") Date birthDate) {
		super();
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

	public void setAccountModels(Set<AccountModel> accountModels) {
		this.accountModels = accountModels;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountModels, birthDate, cpf, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeoplerequestDto other = (PeoplerequestDto) obj;
		return Objects.equals(accountModels, other.accountModels) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(name, other.name);
	}

}
