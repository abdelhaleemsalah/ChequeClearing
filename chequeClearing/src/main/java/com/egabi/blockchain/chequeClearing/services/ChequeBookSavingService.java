package com.egabi.blockchain.chequeClearing.services;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;

public interface ChequeBookSavingService {
	public void saveChequeBook(ChequeBookDetail chequebook);
	ChequeBookDetail selectChequeBySerial(long srNo);
}
