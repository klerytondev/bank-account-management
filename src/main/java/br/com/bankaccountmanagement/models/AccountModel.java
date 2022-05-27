package br.com.bankaccountmanagement.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_ACCOUNT")
public class AccountModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAccount;

	@Column(nullable = false)
	private Double balance = 0.0;

	@Column(nullable = false)
	private Double withdrawalLimit = 0.0;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ActiveFlag activeFlag;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountType accountType;

	private LocalDateTime createdDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "idAccount", foreignKey = @ForeignKey(name = "fk_transactions"))
	private Set<TransactionModel> transactionModels;

	public AccountModel() {
	}

	public AccountModel(Double balance, Double withdrawalLimit, ActiveFlag activeFlag, AccountType accountType,
			LocalDateTime createdDate, Set<TransactionModel> transactionModels) {
		super();
		this.balance = balance;
		this.withdrawalLimit = withdrawalLimit;
		this.activeFlag = activeFlag;
		this.accountType = accountType;
		this.createdDate = createdDate;
		this.transactionModels = transactionModels;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdrawalLimit() {
		return withdrawalLimit;
	}

	public void setWithdrawalLimit(Double withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}

	public ActiveFlag getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(ActiveFlag activeFlag) {
		this.activeFlag = activeFlag;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/*
	 * Salva a data da criação da conta.
	 * */
	public void setCreatedDate() {
		this.createdDate = LocalDateTime.now();
	}

	public Set<TransactionModel> getTransactionModels() {
		return transactionModels;
	}

	public void setTransactionModels(Set<TransactionModel> transactionModels) {
		this.transactionModels = transactionModels;
	}

	public Long getId() {
		return idAccount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, activeFlag, balance, createdDate, idAccount, transactionModels,
				withdrawalLimit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountModel other = (AccountModel) obj;
		return accountType == other.accountType && activeFlag == other.activeFlag
				&& Objects.equals(balance, other.balance) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(idAccount, other.idAccount)
				&& Objects.equals(transactionModels, other.transactionModels)
				&& Objects.equals(withdrawalLimit, other.withdrawalLimit);
	}

}
