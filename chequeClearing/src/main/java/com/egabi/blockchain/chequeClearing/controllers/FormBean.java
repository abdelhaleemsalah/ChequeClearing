package com.egabi.blockchain.chequeClearing.controllers;


public class FormBean {
	private String accountnumber ;
	private long customerid ;
	private String customername ;
	private long branchcode ;
	private String bankid ;
	private long chequeserialNOfrom ;
	private long chequeserialNOto ;
	private String chequecurrency ;
	private String pageName;
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


	}
        
