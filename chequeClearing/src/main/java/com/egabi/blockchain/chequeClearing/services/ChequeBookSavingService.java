package com.egabi.blockchain.chequeClearing.services;


import org.springframework.data.jpa.repository.Query;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;

public interface ChequeBookSavingService {
	public void saveChequeBook(ChequeBookDetail chequebook);
	public ChequeBookDetail selectChequeBySerial(long srNo);
	public ChequeBookDetail findOneWithSRnoAndAccNo(long srNo, Long accNo); 
	 
}
