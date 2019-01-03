package com.egabi.blockchain.chequeClearing.controllers;

public class FormBean {
	private String accountnumber ;

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("properties name=");
        if (accountnumber != null) {
        	sb.append("'").append(accountnumber).append("', ");
//        } else {
//        	sb.append(name).append(", ");
        	}
//        sb.append("age=").append(age).append(", ");
//        sb.append("birthDate=").append(birthDate).append(", ");
//        sb.append("phone=");
//        if (phone != null) {
//        	sb.append("'").append(phone).append("', ");
//        } else {
//        	sb.append(phone).append(", ");
//        }
//        sb.append("currency=").append(currency).append(", ");
//        sb.append("percent=").append(percent).append(", ");
//        sb.append("inquiry=").append(inquiry).append(", ");
//        sb.append("inquiryDetails=");
//        if (inquiryDetails != null) {
//        	sb.append("'").append(inquiryDetails).append("', ");
//        } else {
//        	sb.append(inquiryDetails).append(", ");
//        }
//        sb.append("subscribeNewsletter=").append(subscribeNewsletter).append(", ");
//        sb.append("additionalInfo=").append(additionalInfo);
        return sb.toString();
    }
	}
        
