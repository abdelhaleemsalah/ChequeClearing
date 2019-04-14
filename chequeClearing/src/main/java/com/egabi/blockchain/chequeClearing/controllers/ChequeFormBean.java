package com.egabi.blockchain.chequeClearing.controllers;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
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
	private String fromBankId ;
	private String toBankId ;
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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chequeInitialDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chequeModificationDate;
	
	public Date getChequeInitialDate() {
		return chequeInitialDate;
	}
	public void setChequeInitialDate(Date chequeInitialDate) {
		this.chequeInitialDate = chequeInitialDate;
	}
	public Date getChequeModificationDate() {
		return chequeModificationDate;
	}
	public void setChequeModificationDate(Date chequeModificationDate) {
		this.chequeModificationDate = chequeModificationDate;
	}
	
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
	@NotNull
	public Integer getChequeSerialNoFrom() {
		return chequeSerialNoFrom;
	}
	public void setChequeSerialNoFrom(Integer chequeSerialNoFrom) {
		this.chequeSerialNoFrom = chequeSerialNoFrom;
	}
	@NotNull
	public Integer getChequeSerialNoTo() {
		return chequeSerialNoTo;
	}
	public void setChequeSerialNoTo(Integer chequeSerialNoTo) {
		this.chequeSerialNoTo = chequeSerialNoTo;
	}
	@NotEmpty
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@NotNull
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@NotEmpty(message= "Customer name must be not empty")
	@Size(min = 10, max = 200, message= "Customer name must be between 10 and 200 characters")
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

	public Integer getChequeSerialNo() {
		return chequeSerialNo;
	}
	public void setChequeSerialNo(Integer chequeSerialNo) {
		this.chequeSerialNo = chequeSerialNo;
	}
	@NotNull
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
	public String getFromBankId() {
		return fromBankId;
	}
	public void setFromBankId(String fromBankId) {
		this.fromBankId = fromBankId;
	}
	public String getToBankId() {
		return toBankId;
	}
	public void setToBankId(String toBankId) {
		this.toBankId = toBankId;
	}

	}
        
