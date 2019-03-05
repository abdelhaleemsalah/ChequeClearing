package com.egabi.blockchain.chequeClearing.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CHEQUE_DETAILS database table.
 * 
 */
@Entity
@Table(name="CHEQUE_DETAILS")
@NamedQuery(name="ChequeDetail.findAll", query="SELECT c FROM ChequeDetail c")
public class ChequeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="CHEQUE_AMOUNT")
	private Double chequeAmount;

	@Column(name="CHEQUE_BOOK_ID")
	private long chequeBookId;

	@Column(name="CHEQUE_CURRENCY")
	private String chequeCurrency;

	@Column(name="CHEQUE_DUE_DATE")
	private Timestamp chequeDueDate;

	@Id
	@Column(name="CHEQUE_SR_NO")
	private long chequeSrNo;

	@Column(name="IS_CROSSED")
	private String isCrossed;

	@Column(name="PAY_TO_USERNAME")
	private String payToUsername;
	
	@Column(name="PAY_TO_ACCOUNT_NO")
	private String payToAccountNo;

	@Column(name="FROM_USERNAME")
	private String fromUsername;
	
	@Column(name="CHEQUE_IMAGE_NAME")
	private String chequeImageName;

	private String status;
	
	@Column(name="USER_ID")
	private long userID;
	
	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public ChequeDetail() {
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Double getChequeAmount() {
		return this.chequeAmount;
	}

	public void setChequeAmount(Double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public long getChequeBookId() {
		return this.chequeBookId;
	}

	public void setChequeBookId(long chequeBookId) {
		this.chequeBookId = chequeBookId;
	}

	public String getChequeCurrency() {
		return this.chequeCurrency;
	}

	public void setChequeCurrency(String chequeCurrency) {
		this.chequeCurrency = chequeCurrency;
	}

	public Timestamp getChequeDueDate() {
		return this.chequeDueDate;
	}

	public void setChequeDueDate(Timestamp chequeDueDate) {
		this.chequeDueDate = chequeDueDate;
	}

	public long getChequeSrNo() {
		return this.chequeSrNo;
	}

	public void setChequeSrNo(long chequeSrNo) {
		this.chequeSrNo = chequeSrNo;
	}

	public String getIsCrossed() {
		return this.isCrossed;
	}

	public void setIsCrossed(String isCrossed) {
		this.isCrossed = isCrossed;
	}

	public String getPayToUsername() {
		return this.payToUsername;
	}

	public void setPayToUsername(String payToUsername) {
		this.payToUsername = payToUsername;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayToAccountNo() {
		return payToAccountNo;
	}

	public void setPayToAccountNo(String payToAccountNo) {
		this.payToAccountNo = payToAccountNo;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getChequeImageName() {
		return chequeImageName;
	}

	public void setChequeImageName(String chequeImageName) {
		this.chequeImageName = chequeImageName;
	}

}