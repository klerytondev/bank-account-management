package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class AccountBlockRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "validar a entgrada para bloqueio da account. Só é permitido a entrada: BLOCK.")
	@Pattern(regexp = "BLOCK", message = "{campo.block.obrigatorio}")
	private String accountBlock;

	public AccountBlockRequestDto(
			@Pattern(regexp = "BLOCK", message = "{campo.block.obrigatorio}") String accountBlock) {
		this.accountBlock = accountBlock;
	}

	public String getAccountBlock() {
		return accountBlock;
	}

	public void setAccountBlock(String accountBlock) {
		this.accountBlock = accountBlock;
	}

}
