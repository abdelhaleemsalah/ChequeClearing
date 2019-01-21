package com.egabi.blockchain.chequeClearing.controllers;

import java.sql.Date;

public class ChequeFormBean 
{
	
	private long chequeSerialNofrom ;
	private long chequeSerialNoTo ;
	private String accountNumber ;
	private long customerId ;
	private String customerName ;
	private long branchCode ;
	private String bankId ;
	private long chequeSerialNo ;
	private String chequeCurrency ;
	private String pageName;
	private long chequeAmount;
	private boolean isCrossed;
	private Date chequeDueDate;
	private long chequeBookSerialNo ;
	public long getChequeSerialNofrom() {
		return chequeSerialNofrom;
	}
	public void setChequeSerialNofrom(long chequeSerialNofrom) {
		this.chequeSerialNofrom = chequeSerialNofrom;
	}
	public long getChequeSerialNoTo() {
		return chequeSerialNoTo;
	}
	public void setChequeSerialNoTo(long chequeSerialNoTo) {
		this.chequeSerialNoTo = chequeSerialNoTo;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
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
	public long getChequeSerialNo() {
		return chequeSerialNo;
	}
	public void setChequeSerialNo(long chequeSerialNo) {
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
	public long getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(long chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public boolean isCrossed() {
		return isCrossed;
	}
	public void setCrossed(boolean isCrossed) {
		this.isCrossed = isCrossed;
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
	


	}
        
