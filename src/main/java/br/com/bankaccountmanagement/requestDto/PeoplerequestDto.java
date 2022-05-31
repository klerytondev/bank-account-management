package br.com.bankaccountmanagement.requestDto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Kleryton de souza
 * 
 *          Este DTO recebe os parametros de PeopleModel passados via Json para
 *          criar uma nova pessoa
 */
	public class PeoplerequestDto{

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor
		@Schema(description = "Nome do cliente. ")
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Length(max = 50, message = "{campo.nome.caracteres}")
	private String name;

		@Schema(description = "CPF do cliente. ")
	@CPF(message = "{campo.pessoacpf.nulo}")
	private String cpf;

	@NotNull(message = "{campo.data.obrigatorio}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String birthDate;

	public PeoplerequestDto() {
	}
	
	public PeoplerequestDto(
			@NotEmpty(message = "{campo.nome.obrigatorio}") @Length(max = 50, message = "{campo.nome.caracteres}") String name,
			@CPF(message = "{campo.accountcpf.nulo}") String cpf,
			@NotEmpty(message = "{campo.data.obrigatorio}") String birthDate) {
		this.name = name;
		this.cpf = cpf;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, cpf, name);
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
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(name, other.name);
	}

}
