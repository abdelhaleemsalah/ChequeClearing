package com.egabi.blockchain.chequeClearing.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;

public interface ChequebookRepository extends CrudRepository<ChequeBookDetail,Long> {
		
	@Query(value="SELECT sw FROM ChequeBookDetail sw WHERE sw.accountId=:accountId AND (sw.chequeSrNoFrom <= :chequeSrNoFrom AND sw.chequeSrNoTo >= :chequeSrNoFrom)", nativeQuery = false)
	ChequeBookDetail findOneWithSRnoAndAccNo(@Param("chequeSrNoFrom") long chequeSrNoFrom, @Param("accountId") Long accountId); 
}
