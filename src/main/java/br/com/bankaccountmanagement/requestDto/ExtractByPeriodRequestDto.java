package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Kleryton de souza
 * 
 * Este DTO recebe os parametros passados via Json para realizar busca de transações por periodo
 * */
public class ExtractByPeriodRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Data inicial para extrato por periodo. ")
	@NotEmpty(message = "{campo.initialDate.obrigatorio}")
	private String initialDate;
	
	@Schema(description = "Data final para extrato por periodo. ")
	@NotEmpty(message = "{campo.finalDate.obrigatorio}")
	private String finalDate;
	
	public ExtractByPeriodRequestDto() {
	}

	public ExtractByPeriodRequestDto(@NotEmpty(message = "{campo.initialDate.obrigatorio}") String initialDate,
			@NotEmpty(message = "{campo.finalDate.obrigatorio}") String finalDate) {
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	@Override
	public String toString() {
		return "ExtractByPeriodRequestDto [initialDate=" + initialDate + ", finalDate=" + finalDate + "]";
	}
	
}
