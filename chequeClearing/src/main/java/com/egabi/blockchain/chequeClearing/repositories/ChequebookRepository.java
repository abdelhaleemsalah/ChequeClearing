package com.egabi.blockchain.chequeClearing.repositories;

import org.springframework.data.repository.CrudRepository;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;

public interface ChequebookRepository extends CrudRepository<ChequeBookDetail,Long> {
		
	

}
