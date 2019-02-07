package com.egabi.blockchain.chequeClearing.services;

import java.util.ArrayList;
import java.util.Date;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;

public interface ChequeDetailsSavingService 
{
	public void saveCheque(ChequeDetail cheque);
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatusAndDuedate(long chequeSrNo,String status,Date chequeDueDate);
	public ArrayList<ChequeDetail> findOneWithSRnoAndDuedate(long chequeSrNo,Date chequeDueDate);
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatus(long chequeSrNo,String status);
	public ArrayList<ChequeDetail> findOneWithSRno(long chequeSrNo);
	public ArrayList<ChequeDetail> findOneWithStatusAndDuedate(String status,Date chequeDueDate); 
	public ArrayList<ChequeDetail> findOneWithStatus(String status); 
	public ArrayList<ChequeDetail> findOneWithDuedate(Date chequeDueDate); 
	public ChequeDetail findOneChequeWithSRno(long chequeSrNo);
	public void setUserInfoById(String status ,long chequeSrNo);
	
}
