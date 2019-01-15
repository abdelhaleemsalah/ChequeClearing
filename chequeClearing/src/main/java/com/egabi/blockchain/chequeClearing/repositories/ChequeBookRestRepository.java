package com.egabi.blockchain.chequeClearing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;



public interface ChequeBookRestRepository extends PagingAndSortingRepository<ChequeBookDetail, Long> {
	@Query("select e from ChequeBookDetail e "
	          + "where (e.accountId=:accountId) "
	          + "and (e.chequeSrNoFrom  >= :chequeSrNoFrom and e.chequeSrNoTo <=:chequeSrNoFrom) ")
    List<ChequeBookDetail> findByAccountId(@Param("accountId") String accountId,@Param("chequeSrNoFrom") Long chequeSrNoFrom);
}
