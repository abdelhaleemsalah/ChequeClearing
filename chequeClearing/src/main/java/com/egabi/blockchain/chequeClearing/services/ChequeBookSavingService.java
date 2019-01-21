package com.egabi.blockchain.chequeClearing.services;


import java.util.Optional;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;

public interface ChequeBookSavingService {
	public void saveChequeBook(ChequeBookDetail chequebook);
	public Optional<ChequeBookDetail> selectChequeBySerial(long srNo);
	public ChequeBookDetail findOneWithSRnoAndAccNo(long srNo, Long accNo); 
}
