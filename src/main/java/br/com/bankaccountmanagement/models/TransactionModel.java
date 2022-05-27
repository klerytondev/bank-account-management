package br.com.bankaccountmanagement.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_TRANSACTION")
public class TransactionModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransacao;

	@Column(nullable = false)
	private Double value;

	private LocalDateTime transactionDate;

	public TransactionModel() {
	}

	public TransactionModel(Long idTransacao, Double value) {
		super();
		this.idTransacao = idTransacao;
		this.value = value;
		// Salva a data da transação.
		this.transactionDate = LocalDateTime.now();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public Long getIdTransacao() {
		return idTransacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTransacao, transactionDate, value);
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
		return Objects.equals(idTransacao, other.idTransacao) && Objects.equals(transactionDate, other.transactionDate)
				&& Objects.equals(value, other.value);
	}

}
