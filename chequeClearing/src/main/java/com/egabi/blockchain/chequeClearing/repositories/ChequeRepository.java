package com.egabi.blockchain.chequeClearing.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;

public interface ChequeRepository extends CrudRepository<ChequeDetail,Long> {
		
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo AND sw.status=:status AND sw.chequeDueDate=:chequeDueDate", nativeQuery = false)
	ChequeDetail findOneWithSRnoAndStatusAndDuedate(@Param("chequeSrNo") long chequeSrNo, @Param("status") String status, @Param("chequeDueDate") Date chequeDueDate); 

}
