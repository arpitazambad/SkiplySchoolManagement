package com.skiply.receipt.receipt.model;

import java.time.LocalDateTime;

public class ReceiptResponse {
	
	@Override
	public String toString() {
		return "ReceiptResponse [referenceNumber=" + referenceNumber + ", dateTime=" + dateTime + ", cardNumber="
				+ cardNumber + ", cardType=" + cardType + ", fee=" + fee + ", student=" + student + "]";
	}

	private Long referenceNumber;
	private LocalDateTime dateTime;
	private String cardNumber;
	private String cardType;
	private Long fee;
	
	Student student;

	public Long getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(Long referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
