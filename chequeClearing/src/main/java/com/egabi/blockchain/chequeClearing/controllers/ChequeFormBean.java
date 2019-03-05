package com.egabi.blockchain.chequeClearing.controllers;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ChequeFormBean  implements Serializable
{
	private Integer chequeSerialNoFrom ;
	private Integer chequeSerialNoTo ;
	private String accountNumber ;
	private String paytoAccountNumber ;
	private Integer customerId ;
	private String customerName ;
	private String paytoUsername ;
	private long branchCode ;
	private String bankId ;
	private Integer chequeSerialNo ;
	private String chequeCurrency ;
	private String pageName;
	private Double chequeAmount;
	private boolean crossed;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chequeDueDate;
	private long chequeBookSerialNo ;
	private String chequeStatus;
	private String chequeImageName;
	
	public ChequeFormBean ()
	{       
		this.chequeSerialNo=0;
	}
	public String getChequeStatus() {
		return chequeStatus;
	}
	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}
	public Integer getChequeSerialNoFrom() {
		return chequeSerialNoFrom;
	}
	public void setChequeSerialNoFrom(Integer chequeSerialNoFrom) {
		this.chequeSerialNoFrom = chequeSerialNoFrom;
	}
	public Integer getChequeSerialNoTo() {
		return chequeSerialNoTo;
	}
	public void setChequeSerialNoTo(Integer chequeSerialNoTo) {
		this.chequeSerialNoTo = chequeSerialNoTo;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(long branchCode) {
		this.branchCode = branchCode;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public Integer getChequeSerialNo() {
		return chequeSerialNo;
	}
	public void setChequeSerialNo(Integer chequeSerialNo) {
		this.chequeSerialNo = chequeSerialNo;
	}
	public String getChequeCurrency() {
		return chequeCurrency;
	}
	public void setChequeCurrency(String chequeCurrency) {
		this.chequeCurrency = chequeCurrency;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public Double getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(Double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public boolean isCrossed() {
		return crossed;
	}
	public void setCrossed(boolean crossed) {
		this.crossed = crossed;
	}
	public Date getChequeDueDate() {
		return chequeDueDate;
	}
	public void setChequeDueDate(Date chequeDueDate) {
		this.chequeDueDate = chequeDueDate;
	}
	public long getChequeBookSerialNo() {
		return chequeBookSerialNo;
	}
	public void setChequeBookSerialNo(long chequeBookSerialNo) {
		this.chequeBookSerialNo = chequeBookSerialNo;
	}
	public String getPaytoUsername() {
		return paytoUsername;
	}
	public void setPaytoUsername(String paytoUsername) {
		this.paytoUsername = paytoUsername;
	}
	public String getPaytoAccountNumber() {
		return paytoAccountNumber;
	}
	public void setPaytoAccountNumber(String paytoAccountNumber) {
		this.paytoAccountNumber = paytoAccountNumber;
	}
	public String getChequeImageName() {
		return chequeImageName;
	}
	public void setChequeImageName(String chequeImageName) {
		this.chequeImageName = chequeImageName;
	}

	}
        
