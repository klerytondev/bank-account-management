package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

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
//	@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", message = "Formato de data incorreto")
	private String initialDate;
	
	@ApiModelProperty(notes = "Data final para extrato por periodo. ")
	@NotEmpty(message = "{campo.finalDate.obrigatorio}")
//	@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", message = "Formato de data incorreto")
	private String finalDate;

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
	
}
