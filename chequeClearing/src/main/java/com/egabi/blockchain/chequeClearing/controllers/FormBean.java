package com.egabi.blockchain.chequeClearing.controllers;

import java.math.BigDecimal;

public class FormBean {
	private String accountnumber ;
	private BigDecimal customerid ;
	private String customername ;
	private BigDecimal branchcode ;
	private String bankid ;
	private BigDecimal chequeserialNOfrom ;
	private BigDecimal chequeserialNOto ;
	private String chequecurrency ;
	private String pageName;
	public String getChequecurrency() {
		return chequecurrency;
	}

	public void setChequecurrency(String chequecurrency) {
		this.chequecurrency = chequecurrency;
	}

	private BigDecimal chequebookserialNO ;
	
	public BigDecimal getCustomerid() 
	{
		return customerid;
	}

	public void setCustomerid(BigDecimal customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public BigDecimal getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(BigDecimal branchcode) {
		this.branchcode = branchcode;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public BigDecimal getChequeserialNOfrom() {
		return chequeserialNOfrom;
	}

	public void setChequeserialNOfrom(BigDecimal chequeserialNOfrom) {
		this.chequeserialNOfrom = chequeserialNOfrom;
	}

	public BigDecimal getChequeserialNOto() {
		return chequeserialNOto;
	}

	public void setChequeserialNOto(BigDecimal chequeserialNOto) {
		this.chequeserialNOto = chequeserialNOto;
	}

	

	public BigDecimal getChequebookserialNO() {
		return chequebookserialNO;
	}

	public void setChequebookserialNO(BigDecimal chequebookserialNO) {
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


	}
        
