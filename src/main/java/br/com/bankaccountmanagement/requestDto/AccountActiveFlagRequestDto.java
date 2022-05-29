package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Kleryton de souza
 * 
 * Este DTO recebe os parametros de AccountModel passados via Json para alterar o status da account
 * 
 */
public class AccountActiveFlagRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "validar as possiveis entradas para o status da account. Só são permitidas as "
			+ "entradas: BLOCK|ACTIVE.")
	@NotNull(message = "{campo.accountStatus.obrigatorio}")
	private String accountStatus;
	
	public AccountActiveFlagRequestDto() {
	}

	public AccountActiveFlagRequestDto(@NotNull(message = "{campo.accountStatus.obrigatorio}") String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
