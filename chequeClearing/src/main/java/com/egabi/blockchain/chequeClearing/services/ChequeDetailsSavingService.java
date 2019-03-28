package com.egabi.blockchain.chequeClearing.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.repository.query.Param;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;

public interface ChequeDetailsSavingService 
{
	public void saveCheque(ChequeDetail cheque);
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatusAndDuedate(long chequeSrNo,String status,Date chequeDueDate);
	public ArrayList<ChequeDetail> findOneWithSRnoAndDuedate(long chequeSrNo,Date chequeDueDate);
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatusAndUserId(long chequeSrNo,String status,long userID);
	public ArrayList<ChequeDetail> findOneWithSRnoAndUserId(long chequeSrNo,long userID);
	public ArrayList<ChequeDetail> findOneWithStatusAndDuedate(String status,Date chequeDueDate); 
	public ArrayList<ChequeDetail> findOneWithStatus(String status); 
	public ArrayList<ChequeDetail> findOneWithDuedate(Date chequeDueDate); 
	public ArrayList<ChequeDetail> findOneWithSRno(long chequeSrNo);
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatus(long chequeSrNo,String status);
	public ArrayList<ChequeDetail> findOneWithStatusAndUserId(String status,long userID); 
	public ChequeDetail findOneChequeWithSRno(long chequeSrNo);
	public void setChequeInfoById(String status ,Date chequeModificationDate, long chequeSrNo);
	public void updateChequeById(Date chequeDueDate,long chequeSrNo,Double chequeAmount,
	String isCrossed,String payToUsername,String status,String chequeImageName);
	
	
	
}
