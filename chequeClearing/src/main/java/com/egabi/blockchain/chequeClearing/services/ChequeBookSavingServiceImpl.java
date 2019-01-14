package com.egabi.blockchain.chequeClearing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.repositories.ChequebookRepository;

@Service
public class ChequeBookSavingServiceImpl implements ChequeBookSavingService{

	@Autowired
	private ChequebookRepository chequebookRepo;
	
	@Override
	@Transactional
	public void saveChequeBook(ChequeBookDetail chequebook) {
		// TODO Auto-generated method stub
		chequebookRepo.save(chequebook);
	}
	@Override
	public ChequeBookDetail selectChequeBySerial(long srNo) {
		// TODO Auto-generated method stub
		ChequeBookDetail selectedCheque=new ChequeBookDetail();
		selectedCheque=chequebookRepo.findOne(srNo);
		return selectedCheque;
	}
	@Override
	public ChequeBookDetail findOneWithSRnoAndAccNo(long srNo, Long accNo)
	{
		ChequeBookDetail chequebook= chequebookRepo.findOneWithSRnoAndAccNo(srNo, accNo);
		return chequebook;
	}
}
