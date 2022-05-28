package br.com.bankaccountmanagement.requestDto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

/*
 * Esta classe Transferi e manipula atributos recebidos por parametro da account via controller
 * */

public class TransactionRequestDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor

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
