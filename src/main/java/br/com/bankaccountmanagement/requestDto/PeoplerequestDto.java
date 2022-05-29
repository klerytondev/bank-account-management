package br.com.bankaccountmanagement.requestDto;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bankaccountmanagement.models.AccountModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Kleryton de souza
 * 
 * Este DTO recebe os parametros de PeopleModel passados via Json para criar uma nova pessoa
 * */
public class PeoplerequestDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor
	@ApiModelProperty(notes = "Nome do cliente. ")
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Length(max = 50, message = "{campo.nome.caracteres}")
	private String name;

	@ApiModelProperty(notes = "CPF do cliente. ")
	@CPF(message = "{campo.pessoacpf.nulo}")
	private String cpf;

	private Set<AccountModel> accountModels;

	@NotNull(message = "{campo.data.obrigatorio}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String birthDate;

	public PeoplerequestDto() {
	}

	public PeoplerequestDto(
			@NotEmpty(message = "{campo.nome.obrigatorio}") @Length(max = 50, message = "{campo.nome.caracteres}") String name,
			@CPF(message = "{campo.accountcpf.nulo}") String cpf, Set<AccountModel> accountModels,
			@NotEmpty(message = "{campo.data.obrigatorio}") String birthDate) {
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
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
