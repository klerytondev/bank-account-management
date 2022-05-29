package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Kleryton de souza
 * 
 * */
public class ExtractByPeriodRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Data inicial para extrato por periodo. ")
	@NotEmpty(message = "{campo.initialDate.obrigatorio}")
	private LocalDateTime initialDate;
	
	@ApiModelProperty(notes = "Data final para extrato por periodo. ")
	@NotEmpty(message = "{campo.finalDate.obrigatorio}")
	private LocalDateTime finalDate;

	public ExtractByPeriodRequestDto(@NotEmpty(message = "{campo.initialDate.obrigatorio}") LocalDateTime initialDate,
			@NotEmpty(message = "{campo.finalDate.obrigatorio}") LocalDateTime finalDate) {
		super();
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}

	public LocalDateTime getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(LocalDateTime initialDate) {
		this.initialDate = initialDate;
	}

	public LocalDateTime getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDateTime finalDate) {
		this.finalDate = finalDate;
	}
	
}
