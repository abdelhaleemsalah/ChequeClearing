package com.egabi.blockchain.chequeClearing.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
public class ChequeFormBean 
{
	
	
	private Integer chequeSerialNoFrom ;
	
	private Integer chequeSerialNoTo ;
	private String accountNumber ;
	
	private Integer customerId ;
	private String customerName ;
	private long branchCode ;
	private String bankId ;
	private Integer chequeSerialNo ;
	private String chequeCurrency ;
	private String pageName;
	private Double chequeAmount;
	private boolean crossed;

	private Date chequeDueDate;
	private long chequeBookSerialNo ;
	
//	public ChequeFormBean ()
//	{
//	//	chequeDueDate=new java.sql.Date(utilDate.getTime());
//				
//		chequeDueDate = new java.util.Date();
//		
//////		         System.out.println("Time in java.util.Date is : " + uDate);
//////		
////		         DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
//////		 
////		         System.out.println("Using a dateFormat date is : " + df.format(chequeDueDate));
//		         
//	}
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
	


	}
        
