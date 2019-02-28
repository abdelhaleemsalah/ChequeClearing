package com.egabi.blockchain.chequeClearing.repositories;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;

public interface ChequeRepository extends CrudRepository<ChequeDetail,Long> {
		
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo AND sw.status=:status AND sw.chequeDueDate=:chequeDueDate", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithSRnoAndStatusAndDuedate(@Param("chequeSrNo") long chequeSrNo, @Param("status") String status, @Param("chequeDueDate") Date chequeDueDate); 
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo AND sw.chequeDueDate=:chequeDueDate", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithSRnoAndDuedate(@Param("chequeSrNo") long chequeSrNo, @Param("chequeDueDate") Date chequeDueDate);
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo AND sw.status=:status", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithSRnoAndStatus(@Param("chequeSrNo") long chequeSrNo, @Param("status") String status);
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithSRno(@Param("chequeSrNo") long chequeSrNo);
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.status=:status AND sw.chequeDueDate=:chequeDueDate order by sw.chequeSrNo ASC", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithStatusAndDuedate(@Param("status") String status, @Param("chequeDueDate") Date chequeDueDate); 
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.status=:status order by sw.chequeSrNo ASC", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithStatus(@Param("status") String status); 
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.status=:status AND sw.userID=:userID order by sw.chequeSrNo ASC", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithStatusAndUserId(@Param("status") String status, @Param("userID") long userID); 
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeDueDate=:chequeDueDate order by sw.chequeSrNo ASC", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithDuedate(@Param("chequeDueDate") Date chequeDueDate); 
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo AND sw.status=:status AND sw.userID=:userID", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithSRnoAndStatusAndUserId(@Param("chequeSrNo") long chequeSrNo, @Param("status") String status, @Param("userID") long userID);
	
	@Query(value="SELECT sw FROM ChequeDetail sw WHERE sw.chequeSrNo=:chequeSrNo AND sw.userID=:userID", nativeQuery = false)
	ArrayList<ChequeDetail> findOneWithSRnoAndUserId(@Param("chequeSrNo") long chequeSrNo, @Param("userID") long userID);
	
	@Modifying
	@Transactional
	@Query(value="update ChequeDetail c set c.status=:status where c.chequeSrNo=:chequeSrNo", nativeQuery = false)
	void setUserInfoById(@Param("status") String status ,@Param("chequeSrNo") long chequeSrNo);
	
	@Modifying
	@Transactional
	@Query(value="update ChequeDetail c set c.chequeDueDate=:chequeDueDate, c.chequeAmount=:chequeAmount , "
	+ "c.isCrossed=:isCrossed , c.payToUsername=:payToUsername , c.status=:status where "
	+ "c.chequeSrNo=:chequeSrNo", nativeQuery = false)
	void updateChequeById(@Param("chequeDueDate") Date chequeDueDate, @Param("chequeSrNo") 
	long chequeSrNo, @Param("chequeAmount") Double chequeAmount, @Param("isCrossed") String isCrossed,
	@Param("payToUsername") String payToUsername,@Param("status") String status);
	
}



