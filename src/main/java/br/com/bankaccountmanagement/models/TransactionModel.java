package br.com.bankaccountmanagement.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Kleryton de souza
 */

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_TRANSACTION")
public class TransactionModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransaction;

	@Column(name = "account_id")
	private Long idAccount;

	@Column(nullable = false)
	private Double value;

	private LocalDate transactionDate;

	public TransactionModel() {
		// Salva a data da transação.
		this.transactionDate = LocalDate.now();
	}

	public TransactionModel(Long idTransacao, Double value) {
		this.idTransaction = idTransacao;
		this.value = value;
		// Salva a data da transação.
		this.transactionDate = LocalDate.now();

	}

	public TransactionModel(Long idTransaction, Long idAccount, Double value) {
		this.idTransaction = idTransaction;
		this.value = value;
		this.idAccount = idAccount;
		// Salva a data da transação.
		this.transactionDate = LocalDate.now();

	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public Long getIdTransacao() {
		return idTransaction;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTransaction, transactionDate, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionModel other = (TransactionModel) obj;
		return Objects.equals(idTransaction, other.idTransaction)
				&& Objects.equals(transactionDate, other.transactionDate) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "TransactionModel [idTransacao=" + idTransaction + ", value=" + value + ", transactionDate="
				+ transactionDate + "]";
	}

}
