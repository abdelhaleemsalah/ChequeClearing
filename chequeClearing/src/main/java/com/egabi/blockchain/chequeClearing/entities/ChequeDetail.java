package com.egabi.blockchain.chequeClearing.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
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

	@Column(name="BRANCH_ID")
	private BigDecimal branchId;

	@Column(name="CHEQUE_AMOUNT")
	private BigDecimal chequeAmount;

	@Column(name="CHEQUE_BOOK_ID")
	private BigDecimal chequeBookId;

	@Column(name="CHEQUE_CURRENCY")
	private String chequeCurrency;

	@Column(name="CHEQUE_DUE_DATE")
	private Timestamp chequeDueDate;

	@Id
	@Column(name="CHEQUE_SR_NO")
	private BigDecimal chequeSrNo;

	@Column(name="IS_CROSSED")
	private String isCrossed;

	@Column(name="PAY_TO_USERNAME")
	private String payToUsername;

	private String status;

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

	public BigDecimal getBranchId() {
		return this.branchId;
	}

	public void setBranchId(BigDecimal branchId) {
		this.branchId = branchId;
	}

	public BigDecimal getChequeAmount() {
		return this.chequeAmount;
	}

	public void setChequeAmount(BigDecimal chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public BigDecimal getChequeBookId() {
		return this.chequeBookId;
	}

	public void setChequeBookId(BigDecimal chequeBookId) {
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

	public BigDecimal getChequeSrNo() {
		return this.chequeSrNo;
	}

	public void setChequeSrNo(BigDecimal chequeSrNo) {
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

}