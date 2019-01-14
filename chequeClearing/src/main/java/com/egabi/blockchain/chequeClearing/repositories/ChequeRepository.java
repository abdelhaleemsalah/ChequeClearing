package com.egabi.blockchain.chequeClearing.repositories;

import org.springframework.data.repository.CrudRepository;
import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;

public interface ChequeRepository extends CrudRepository<ChequeDetail,Long> {
		
}
