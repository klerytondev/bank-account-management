package br.com.bankaccountmanagement.requestDto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Kleryton de souza
 * 
 * Este DTO recebe os parametros de TransactionModel passados via Json para criar uma nova transação
 * */

public class TransactionRequestDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor

	@Schema(description = "Valor da transação. ")
	@NotEmpty(message = "{campo.valorsaque.obrigatorio}")
	private Double value;

	public TransactionRequestDto() {
	}

	public TransactionRequestDto(@NotEmpty(message = "{campo.valor.obrigatorio}") Double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionRequestDto other = (TransactionRequestDto) obj;
		return Objects.equals(value, other.value);
	}

}
