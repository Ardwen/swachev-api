package com.evliion.ev.payload;

public class TransactionResponse {
	private Long transactionId;
	private Boolean status;

	public TransactionResponse(Long transactionId, Boolean status) {
		super();
		this.transactionId = transactionId;
		this.status = status;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}