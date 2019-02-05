package com.egabi.blockchain.chequeClearing.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
	@Override
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatusAndDuedate(long chequeSrNo,String status,Date chequeDueDate)
	{
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithSRnoAndStatusAndDuedate(chequeSrNo,status,chequeDueDate);
		return cheques;
	}
	@Override
	public ArrayList<ChequeDetail> findOneWithSRnoAndDuedate(long chequeSrNo, Date chequeDueDate)
	{
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithSRnoAndDuedate(chequeSrNo,chequeDueDate);
		return cheques;
	}
	@Override
	public ArrayList<ChequeDetail> findOneWithSRnoAndStatus(long chequeSrNo, String status)
	{
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithSRnoAndStatus(chequeSrNo,status);
		return cheques;
	}
	@Override
	public ArrayList<ChequeDetail> findOneWithSRno(long chequeSrNo)
	{
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithSRno(chequeSrNo);
		return cheques;
	}
	@Override
	public ArrayList<ChequeDetail> findOneWithStatusAndDuedate(String status, Date chequeDueDate)
	{
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithStatusAndDuedate(status, chequeDueDate);
		return cheques;
	}
	@Override
	public ArrayList<ChequeDetail> findOneWithStatus(String status) 
	{
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithStatus(status);
		return cheques;
	}
	@Override
	public ArrayList<ChequeDetail> findOneWithDuedate(Date chequeDueDate) {
		// TODO Auto-generated method stub
		ArrayList<ChequeDetail> cheques= chequeRepo.findOneWithDuedate(chequeDueDate);
		return cheques;
	}

}
