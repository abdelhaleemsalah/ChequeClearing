package com.egabi.blockchain.chequeClearing.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CHEQUE_BOOK_DETAILS database table.
 * 
 */
@Entity
@Table(name="CHEQUE_BOOK_DETAILS")
@NamedQuery(name="ChequeBookDetail.findAll", query="SELECT c FROM ChequeBookDetail c")
public class ChequeBookDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_ID")
	private BigDecimal accountId;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BRANCH_ID")
	private BigDecimal branchId;

	@Id
	@Column(name="CHEQUE_BOOK_ID")
	private BigDecimal chequeBookId;

	@Column(name="CHEQUE_SR_NO_FROM")
	private BigDecimal chequeSrNoFrom;

	@Column(name="CHEQUE_SR_NO_TO")
	private BigDecimal chequeSrNoTo;

	private String currency;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	public ChequeBookDetail() {
	}

	public BigDecimal getAccountId() {
		return this.accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
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

	public BigDecimal getChequeBookId() {
		return this.chequeBookId;
	}

	public void setChequeBookId(BigDecimal chequeBookId) {
		this.chequeBookId = chequeBookId;
	}

	public BigDecimal getChequeSrNoFrom() {
		return this.chequeSrNoFrom;
	}

	public void setChequeSrNoFrom(BigDecimal chequeSrNoFrom) {
		this.chequeSrNoFrom = chequeSrNoFrom;
	}

	public BigDecimal getChequeSrNoTo() {
		return this.chequeSrNoTo;
	}

	public void setChequeSrNoTo(BigDecimal chequeSrNoTo) {
		this.chequeSrNoTo = chequeSrNoTo;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}