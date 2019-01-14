package com.egabi.blockchain.chequeClearing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;
import com.egabi.blockchain.chequeClearing.repositories.ChequeRepository;

@Repository
public class ChequeDetailsSavingServiceImpl implements ChequeDetailsSavingService{

	@Autowired
	private ChequeRepository chequeRepo;
	
	@Override
	public void saveCheque(ChequeDetail cheque) {
		// TODO Auto-generated method stub
		chequeRepo.save(cheque);
	}

}
