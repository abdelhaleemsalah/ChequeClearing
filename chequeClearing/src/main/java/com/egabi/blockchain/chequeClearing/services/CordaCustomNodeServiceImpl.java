package com.egabi.blockchain.chequeClearing.services;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import com.egabi.blockchain.chequeClearing.controllers.ChequeFormBean;
import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeServiceImpl;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import com.template.flow.ChequeBookRegisterationFlow;
import com.template.schema.IOUSchemaV1;
import com.template.state.ChequeBookState;

import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.messaging.FlowHandle;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.Builder;
import net.corda.core.node.services.vault.CriteriaExpression;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;

public class CordaCustomNodeServiceImpl extends CordaNodeServiceImpl {

		public CordaCustomNodeServiceImpl(NodeRpcConnection nodeRpcConnection){
			super(nodeRpcConnection);
		}
	    /** dummy method 
	     * @throws SecurityException 
	     * @throws NoSuchFieldException */
		
		
		public ChequeFormBean retrieveChequeBook(String searchBankId,long accNo,long serialno ) throws NoSuchFieldException, SecurityException
		{
			ChequeFormBean singleChequeFormBean=new ChequeFormBean();
			   CordaRPCOps proxy= this.getNodeRpcConnection().getProxy();	
			   Set<Party> parties= proxy.partiesFromName(searchBankId,  true);
			    final Party bankIdentity = parties.iterator().next();
			   
			    Stream<StateAndRef<ChequeBookState>> statesAndRefs=proxy.vaultQuery(ChequeBookState.class).getStates().stream()
	           .filter(it -> it.getState().getData().getRegisterBank().equals(bankIdentity));
			   
			    
			    QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
			    Field registerBank = IOUSchemaV1.PersistentIOU.class.getDeclaredField("registerBank");
		        CriteriaExpression registerBankIndex = Builder.equal(registerBank, bankIdentity.getName().toString());
		        QueryCriteria lenderCriteria = new QueryCriteria.VaultCustomQueryCriteria(registerBankIndex);
		        QueryCriteria criteria = generalCriteria.and(lenderCriteria);
		        List<StateAndRef<ChequeBookState>> results = proxy.vaultQueryByCriteria(criteria,ChequeBookState.class).getStates();
		        
		        for(StateAndRef<ChequeBookState> chequeBook:results)
		        {
		        	if( chequeBook.getState().getData().getAccountNumber().equals(String.valueOf(accNo)))
			    	{
			    		if(chequeBook.getState().getData().getChequeSerialNofrom()<=serialno &&chequeBook.getState().getData().getChequeSerialNoTo()>=serialno  )
			    		{
			    			singleChequeFormBean.setChequeSerialNo(serialno);
					    	singleChequeFormBean.setCustomerName(chequeBook.getState().getData().getCustomerName());
					    	singleChequeFormBean.setAccountNumber(String.valueOf(chequeBook.getState().getData().getAccountNumber()));
					    	singleChequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency());
					    	singleChequeFormBean.setBankId(String.valueOf(chequeBook.getState().getData().getBankId()));
					    	singleChequeFormBean.setBranchCode(chequeBook.getState().getData().getBranchCode());
					    	//model.addAttribute("formBean",singleChequeFormBean);
			    		}
			    	}
		        }
		        return singleChequeFormBean;
		}
		
		
		
	    public Boolean registerChequeBook(ChequeFormBean formBean,String registerBankId){
	    
	    	
	    	CordaRPCOps proxy= this.getNodeRpcConnection().getProxy();		   
			   Set<Party> parties= proxy.partiesFromName(registerBankId,  true);
			   Set<Party> cbeParty= proxy.partiesFromName("CBE",  true);
			   final Party myIdentity = parties.iterator().next();
			   final Party cbeIdentity = cbeParty.iterator().next();
			   if(parties.isEmpty())
			   {
				  throw new IllegalArgumentException("Target string " +"pARTA"+" doesnt match any nodes on the network.");
			   }
			   else if (parties.size()>1)
			   {
		          throw new IllegalArgumentException("Target string " +"pARTA "+" matches multiple nodes on the network.");
			   }
			   
//			   Party registerBank,
//	           Party cbeBank, long chequeSerialNofrom, long chequeSerialNoTo, String accountNumber, long customerId,
//	           String customerName, long branchCode, String bankId, String chequeCurrency, long chequeBookSerialNo,
//	           UniqueIdentifier linearId)
			   
			   
			   ChequeBookState state=new ChequeBookState(myIdentity,cbeIdentity,formBean.getChequeSerialNoFrom(),formBean.getChequeSerialNoTo(),formBean.getAccountNumber(),
					   formBean.getCustomerId(),formBean.getCustomerName(),formBean.getBranchCode(),formBean.getBankId(),formBean.getChequeCurrency(),formBean.getChequeSerialNo(),new UniqueIdentifier());
			   Response Responsestatus=null;
		        try {
		            final FlowHandle<SignedTransaction> flowHandle = proxy.startFlowDynamic(
		            		ChequeBookRegisterationFlow.Initiator.class,
		            		state, cbeIdentity);

		            final SignedTransaction result = flowHandle.getReturnValue().get();
		            final String msg = String.format("Transaction id %s committed to ledger.\n%s",
		                    result.getId(), result.getTx().getOutputStates().get(0));
		            Responsestatus=Response.status(CREATED).entity(msg).build();
		        } catch (Exception e) {
		        	Responsestatus= Response.status(BAD_REQUEST).entity(e.getMessage()).build();
		        }
	    	
	    	return true;}
	}