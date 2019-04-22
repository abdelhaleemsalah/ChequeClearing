package com.egabi.blockchain.chequeClearing.services;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarInputStream;
import java.util.stream.Stream;
import java.time.LocalDate;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import com.egabi.blockchain.chequeClearing.controllers.ChequeFormBean;
import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeServiceImpl;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import com.template.flow.ChequeBookRegisterationFlow;
import com.template.flow.ChequeSubmitFlow;
import com.template.schema.ChequeSchema;
import com.template.schema.IOUSchemaV1;
import com.template.state.ChequeBookState;
import com.template.state.ChequeState;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.crypto.SecureHash;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.messaging.FlowHandle;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.Builder;
import net.corda.core.node.services.vault.ColumnPredicate;
import net.corda.core.node.services.vault.CriteriaExpression;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import  net.corda.core.internal.InputStreamAndHash;
public class CordaCustomNodeServiceImpl extends CordaNodeServiceImpl {

	@Autowired
	ResourceLoader resourceLoader;
		public CordaCustomNodeServiceImpl(NodeRpcConnection nodeRpcConnection){
			super(nodeRpcConnection);
		}
	    /** dummy method 
	     * @throws SecurityException 
	     * @throws NoSuchFieldException */
		
		// 
		
		public ArrayList<ChequeFormBean> retreieveSubmittedCheque(String searchBankId , Date chequeDueDate ) throws NoSuchFieldException, SecurityException
		{
			ArrayList<ChequeFormBean> result= new ArrayList<ChequeFormBean>();
			ChequeFormBean singleChequeFormBean=new ChequeFormBean();
			   CordaRPCOps proxy= this.getNodeRpcConnection().getProxy();	
			   Set<Party> parties= proxy.partiesFromName(searchBankId,  true);
			    final Party bankIdentity = parties.iterator().next();
			    
			    
			    QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
			    Field toBank = ChequeSchema.PersistentIOU.class.getDeclaredField("payToUserBankName");
			    Field dueDate = ChequeSchema.PersistentIOU.class.getDeclaredField("chequeDueDate");
		        CriteriaExpression toBankIndex = Builder.equal(toBank, searchBankId);
		        
		        Calendar c = Calendar.getInstance(); 
		 	   c.setTime(chequeDueDate);
		 	   
		 	   
		 		LocalDate dueDateLocal = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
		        
		        
		        CriteriaExpression dueDateIndex = Builder.equal(dueDate, dueDateLocal);
		        QueryCriteria toBankCriteria = new QueryCriteria.VaultCustomQueryCriteria(toBankIndex);
		        QueryCriteria dueDateCriteria = new QueryCriteria.VaultCustomQueryCriteria(dueDateIndex);
		        QueryCriteria criteria = generalCriteria.and(toBankCriteria).and(dueDateCriteria);
		        
		        List<StateAndRef<ChequeState>> results = proxy.vaultQueryByCriteria(criteria,ChequeState.class).getStates();
		        
//			    List<StateAndRef<ChequeState>> statesAndRefs=proxy.vaultQuery(ChequeState.class).getStates().stream()
//			    		.filter(it -> it.getState().getData().getChequeDueDate().equals(chequeDueDate));
			    for(StateAndRef<ChequeState> chequeBook:results)
		        {
			    	ChequeFormBean chequeFormBean= new ChequeFormBean();
			    	if(chequeBook.getState().getData().getFromBank() !=null)
			    	{
			    		chequeFormBean.setFromBankId(chequeBook.getState().getData().getFromBank().getName().toString() );
			    	}
			    	if(chequeBook.getState().getData().getToBank() !=null)
			    	{
			    		chequeFormBean.setToBankId(chequeBook.getState().getData().getToBank().getName().toString() );
			    	}
			    	if(chequeBook.getState().getData().getChequeSerialNumber()!=0)
			    	{
			    		chequeFormBean.setChequeSerialNo(Integer.parseInt(String.valueOf(chequeBook.getState().getData().getChequeSerialNumber())));
			    	}
			    	if(chequeBook.getState().getData().getPayToUserAccount()!=null)
			    	{
			    		chequeFormBean.setPaytoAccountNumber(chequeBook.getState().getData().getPayToUserAccount());
			    	}
			    	if(chequeBook.getState().getData().getPayToUserName()!=null)
			    	{
			    		chequeFormBean.setPaytoUsername(chequeBook.getState().getData().getPayToUserName());
			    	}
			    	if(chequeBook.getState().getData().getPayToUserBankName()!=null)
			    	{
			    		chequeFormBean.setToBankId(chequeBook.getState().getData().getPayToUserBankName());
			    	}
			    	
			    	if(chequeBook.getState().getData().getChequeAmount()!=0)
			    	{
			    		chequeFormBean.setChequeAmount(Double.parseDouble(String.valueOf(chequeBook.getState().getData().getChequeAmount())));
			    	}
			    	
			    	if(chequeBook.getState().getData().getChequeDueDate()!=null)
			    	{
			    		int year =chequeBook.getState().getData().getChequeDueDate().getYear();
			    		int month =chequeBook.getState().getData().getChequeDueDate().getMonthValue();
			    		int day =chequeBook.getState().getData().getChequeDueDate().getDayOfMonth();
			    		chequeFormBean.setChequeDueDate(new  Date(year, month, day)      );
			    	}
			    	if(chequeBook.getState().getData().getChequeCurrency()!=null)
			    	{
			    		chequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency()) ;    
			    	
			    	}
			    	
			     	if(chequeBook.getState().getData().getChequeCurrency()!=null)
			    	{
			    		chequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency()) ;    
			    	
			    	}
			     	if(chequeBook.getState().getData().getChequeOwnerAccount()!=null)
			    	{
			    		chequeFormBean.setAccountNumber(Integer.valueOf(chequeBook.getState().getData().getChequeOwnerAccount())) ;    
			    	
			    	}
			     	
			      	if(chequeBook.getState().getData().getChequeOwnerName()!=null)
			    	{
			    		chequeFormBean.setCustomerName(chequeBook.getState().getData().getChequeOwnerName()) ;    
			    	
			    	}
			      	if(chequeBook.getState().getData().getChequeOwnerBankName()!=null)
			    	{
			    		chequeFormBean.setFromBankId(chequeBook.getState().getData().getChequeOwnerBankName()) ;    
			    	
			    	}
			      	
			    	/*
	
        @Column(name = "chequeOwnerAccount") private  String chequeOwnerAccount;
        @Column(name = "chequeOwnerName") private  String chequeOwnerName;
        @Column(name = "chequeOwnerBankName") private  String chequeOwnerBankName;
			    	 */
			    	System.out.println("chque amount " +chequeBook.getState().getData().getChequeAmount());
			    	result.add(chequeFormBean);
		        }
			
			return result;
		}
		public ChequeFormBean retrieveChequeBook(String searchBankId,long accNo,Integer serialno ) throws NoSuchFieldException, SecurityException
		{
			ChequeFormBean singleChequeFormBean=new ChequeFormBean();
			   CordaRPCOps proxy= this.getNodeRpcConnection().getProxy();	
			   Set<Party> parties= proxy.partiesFromName(searchBankId,  true);
			    final Party bankIdentity = parties.iterator().next();
			   
//			    Stream<StateAndRef<ChequeBookState>> statesAndRefs=proxy.vaultQuery(ChequeBookState.class).getStates().stream()
//	           .filter(it -> it.getState().getData().getRegisterBank().equals(bankIdentity));
			   
			    
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
					    	singleChequeFormBean.setAccountNumber(Integer.valueOf(chequeBook.getState().getData().getAccountNumber()));
					    	singleChequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency());
					    	singleChequeFormBean.setFromBankId(String.valueOf(chequeBook.getState().getData().getBankId()));
					    	singleChequeFormBean.setBranchCode(chequeBook.getState().getData().getBranchCode());
					    	singleChequeFormBean.setChequeSerialNoFrom(Integer.parseInt(String.valueOf(chequeBook.getState().getData().getChequeSerialNofrom())));
					    	singleChequeFormBean.setChequeSerialNoTo(Integer.parseInt(String.valueOf(chequeBook.getState().getData().getChequeSerialNoTo())));
					    	singleChequeFormBean.setCustomerId(Integer.parseInt(String.valueOf(chequeBook.getState().getData().getCustomerId())));
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
			   
			   
			   ChequeBookState state=new ChequeBookState(myIdentity,cbeIdentity,formBean.getChequeSerialNoFrom(),formBean.getChequeSerialNoTo(),formBean.getAccountNumber().toString(),
					   formBean.getCustomerId(),formBean.getCustomerName(),formBean.getBranchCode(),registerBankId,formBean.getChequeCurrency(),formBean.getChequeSerialNo(),new UniqueIdentifier());
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
	

public void submitCheque(ChequeFormBean formBean, InputStream fileStream,String toBank)
{
	InputStream input = null;
	Properties prop = new Properties();
	String propBankId = "";
	try {
		input = resourceLoader.getResource("classpath:bankconfig.properties").getInputStream();
		prop.load(input);
		System.out.println("prop get bank id: " + prop.getProperty("mybank"));
		propBankId = prop.getProperty("mybank");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	CordaRPCOps proxy= this.getNodeRpcConnection().getProxy();	
	   Set<Party> parties= proxy.partiesFromName(propBankId,  true);
	  Set<Party> toOutOfBeanParty= proxy.partiesFromName(toBank,  true);
	  Set<Party> toParty= proxy.partiesFromName(formBean.getToBankId(),  true);
	   final Party myIdentity = parties.iterator().next();
	   final Party toOutOfBeanIdentity = toOutOfBeanParty.iterator().next();
	   final Party toIdentity = toParty.iterator().next();
	   
//	   ColumnPredicate<java.lang.String> uploaderCondition ;
//	   ColumnPredicate<java.lang.String> filenameCondition;
	   
 // proxy.queryAttachments(AttachmentsQueryCriteria(uploaderCondition,  filenameCondition) );
	   JarInputStream stream=null;
	   
	   
//	  InputStreamAndHash.class.newInstance().c
	   try {
		 stream=new JarInputStream(fileStream);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   
//	SecureHash  hash=proxy.uploadAttachment(fileStream);

		//Creating LocalDate by providing input arguments
	   Calendar c = Calendar.getInstance(); 
	   c.setTime(formBean.getChequeDueDate());
	   
	   
		LocalDate dueDate = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));

	ChequeState state=new ChequeState(myIdentity,toIdentity,Long.parseLong(formBean.getChequeSerialNo().toString()),formBean.getPaytoUsername(),formBean.getPaytoAccountNumber(),formBean.getToBankId(),
			formBean.getChequeAmount().longValue(),dueDate,formBean.getChequeCurrency(),formBean.getAccountNumber().toString(),formBean.getCustomerName(),propBankId,new UniqueIdentifier());
	  Response Responsestatus=null;
	  
        try {
     	 
//            final FlowHandle<SignedTransaction> flowHandle = proxy.startTrackedFlowDynamic(
//            		ChequeSubmitFlow.Initiator.class,
//            		state, toOutOfBeanIdentity,hash);
//            
            final FlowHandle<SignedTransaction> flowHandle = proxy.startTrackedFlowDynamic(
            		ChequeSubmitFlow.Initiator.class,
            		state, toOutOfBeanIdentity);

            final SignedTransaction result = flowHandle.getReturnValue().get();
            final String msg = String.format("Transaction id %s committed to ledger.\n%s",
                    result.getId(), result.getTx().getOutputStates().get(0));
            Responsestatus=Response.status(CREATED).entity(msg).build();
        } catch (Exception e) {
        	Responsestatus= Response.status(BAD_REQUEST).entity(e.getMessage()).build();
        }

        
        
        
	}
}