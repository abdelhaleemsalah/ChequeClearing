package com.egabi.blockchain.chequeClearing.controllers;

import java.sql.Date;

public class ChequeFormBean 
{
	private long chequeserialNOfrom ;
	private long chequeserialNOto ;
	private String accountnumber ;
	private long customerid ;
	private String customername ;
	private long branchcode ;
	private String bankid ;
	private long chequeserialNO ;
	private String chequecurrency ;
	private String pageName;
	private long chequeAmount;
	private boolean isCrossed;
	private Date chequeDueDate;
	
	
	public Date getChequeDueDate() {
		return chequeDueDate;
	}

	public void setChequeDueDate(Date chequeDueDate) {
		this.chequeDueDate = chequeDueDate;
	}
	
	public long getChequeAmount() {
		return chequeAmount;
	}

	public void setChequeAmount(long chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public boolean getIsCrossed() {
		return isCrossed;
	}

	public void setIsCrossed(boolean isCrossed) {
		this.isCrossed = isCrossed;
	}

	public long getChequeserialNO() {
		return chequeserialNO;
	}

	public void setChequeserialNO(long chequeserialNO) {
		this.chequeserialNO = chequeserialNO;
	}

	public String getChequecurrency() {
		return chequecurrency;
	}

	public void setChequecurrency(String chequecurrency) {
		this.chequecurrency = chequecurrency;
	}

	private long chequebookserialNO ;
	
	public long getCustomerid() 
	{
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public long getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(long branchcode) {
		this.branchcode = branchcode;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public long getChequebookserialNO() {
		return chequebookserialNO;
	}

	public void setChequebookserialNO(long chequebookserialNO) {
		this.chequebookserialNO = chequebookserialNO;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public long getChequeserialNOfrom() {
		return chequeserialNOfrom;
	}

	public void setChequeserialNOfrom(long chequeserialNOfrom) {
		this.chequeserialNOfrom = chequeserialNOfrom;
	}

	public long getChequeserialNOto() {
		return chequeserialNOto;
	}

	public void setChequeserialNOto(long chequeserialNOto) {
		this.chequeserialNOto = chequeserialNOto;
	}


	}
        
