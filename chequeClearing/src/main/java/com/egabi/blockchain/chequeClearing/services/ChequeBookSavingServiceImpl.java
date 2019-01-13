package com.egabi.blockchain.chequeClearing.services;

import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.repositories.ChequebookRepository;

@Service
public class ChequeBookSavingServiceImpl implements ChequeBookSavingService{

	@Autowired
	private ChequebookRepository chequebookRepo;
	
	@PersistenceContext
    private EntityManager em;
	
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
