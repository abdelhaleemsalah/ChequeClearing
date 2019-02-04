package com.egabi.blockchain.chequeClearing.services;

import java.util.Date;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;

public interface ChequeDetailsSavingService 
{
	public void saveCheque(ChequeDetail cheque);
	public ChequeDetail findOneWithSRnoAndStatusAndDuedate(long chequeSrNo,String status,Date chequeDueDate);
}
