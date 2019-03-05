package com.egabi.blockchain.chequeClearing.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;

@Repository
public interface ChequebookRepository extends CrudRepository<ChequeBookDetail,Long> {
		
	@Query(value="SELECT sw FROM ChequeBookDetail sw WHERE sw.accountId=:accountId AND (sw.chequeSrNoFrom <= :chequeSrNoFrom AND sw.chequeSrNoTo >= :chequeSrNoFrom)", nativeQuery = false)
	ChequeBookDetail findOneWithSRnoAndAccNo(@Param("chequeSrNoFrom") long chequeSrNoFrom, @Param("accountId") Long accountId); 
}

