package com.evliion.ev.payload;

public class CardResponse {
	
	private Long paymentMethodId;
	private Long userId;
	private String nameOnCard;
	private String cardNumber;
	private int expiryYear;
	private String expiryMonth;
	
	public CardResponse() {
		
	}
	public CardResponse(Long paymentMethodId, Long userId, String nameOnCard, String cardNumber, int expiryYear,
			String expiryMonth) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.userId = userId;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expiryYear = expiryYear;
		this.expiryMonth = expiryMonth;
	}
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	
}
