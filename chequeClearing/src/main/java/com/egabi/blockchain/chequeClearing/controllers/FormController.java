package com.egabi.blockchain.chequeClearing.controllers;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.CordaCustomNodeServiceImpl;
import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import com.template.flow.ChequeBookRegisterationFlow;
import com.template.state.ChequeBookState;

import kotlin.Suppress;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.messaging.FlowHandle;
import net.corda.core.transactions.SignedTransaction;

@Controller
@RequestMapping("/{username}/Registeration")
@SessionAttributes("formBean")
//@PropertySource("classpath:resources/bankconfig.properties")
public class FormController {

	// Invoked on every request

	
	  @Autowired
	   @Suppress(names="SpringJavaInjectionPointsAutowiringInspection")
	   private  Map<String,CordaCustomNodeServiceImpl> services;
	  
	  
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
    private ChequeBookSavingService chequeBookSavingService;
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}


	   
	  @Autowired
	  @Qualifier("HSBCRpcConnection")
	  private NodeRpcConnection rpcConnection ;
	   
	@ModelAttribute("formBean")
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}
	
	@PostMapping
	public String processSubmit(@Valid 	ChequeFormBean  formBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model) {
		
		InputStream input = null;
		Properties prop=new Properties();
		String propBankId="";
		try 
		{
			input=resourceLoader.getResource("classpath:bankconfig.properties").getInputStream();
			prop.load(input);
			System.out.println("prop get bank id: "+prop.getProperty("mybank"));	
			propBankId=prop.getProperty("mybank");
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("inside processSubmit");
	//	System.out.println("pageName            "+page);
		String returnPage=null;
//		if(page.equalsIgnoreCase("RegConfirmation"))
//		{
//			returnPage= "hello";
//		}
//		else if(page.equalsIgnoreCase("RegSummary"))
//		{
//			System.out.println("account id: "+formBean.getAccountnumber());
//	    	
//			ChequeBookDetail newCreatedChequeBook=new ChequeBookDetail();
//			newCreatedChequeBook.setAccountId(Long.valueOf(formBean.getAccountnumber()));
//			newCreatedChequeBook.setBankCode(formBean.getBankid());
//			newCreatedChequeBook.setBranchId(Long.valueOf("77"));
//			newCreatedChequeBook.setChequeBookId(Long.valueOf("3"));
//			newCreatedChequeBook.setChequeSrNoFrom(formBean.getChequeserialNOfrom());
//			newCreatedChequeBook.setChequeSrNoTo(formBean.getChequeserialNOto());
//			newCreatedChequeBook.setCustomerName(formBean.getCustomername());
//			newCreatedChequeBook.setCurrency(formBean.getChequecurrency());
//
//			chequeBookSavingService.saveChequeBook(newCreatedChequeBook);
//	    	
//			returnPage= "RegSummary";
		//}
		
//		else if(page.equalsIgnoreCase("hello"))
//		{
		
		if(!model.containsAttribute("formBean"))
			{
			model.addAttribute("formBean", new ChequeFormBean());
			}
		System.out.println("account id: "+formBean.getAccountNumber());
		
		if (result.hasErrors()) {
			System.out.println("pageName has error binding            ");
			return null;
		}
		//model.addAttribute("formBean",formBean);
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		System.out.println("account number "+formBean.getAccountNumber());
//		if (ajaxRequest) {
//			System.out.println("page has ajax            ");
//			// prepare model for rendering success message in this request
//			model.addAttribute("message", message);
//			return null;
//		} 
//		else 
//		{
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			//redirectAttrs.addFlashAttribute("message", message);
			System.out.println("inside process submit");
			
			model.addAttribute("formBean",formBean);
			
			
			
//			   CordaNodeService PartyA=  services.get(formBean.getBankId()+"NodeService");
//			   
//			   PartyA.peerNames();
			
			
			CordaCustomNodeServiceImpl PartyA=  services.get(propBankId+"NodeService");
			 
			   
			// 
			   PartyA.registerChequeBook(formBean, formBean.getBankId());
			// 
//			   CordaRPCOps proxy= rpcConnection.getProxy();		   
//			   Set<Party> parties= proxy.partiesFromName(propBankId,  true);
//			   Set<Party> cbeParty= proxy.partiesFromName("CBE",  true);
//			   final Party myIdentity = parties.iterator().next();
//			   final Party cbeIdentity = cbeParty.iterator().next();
//			   if(parties.isEmpty())
//			   {
//				  throw new IllegalArgumentException("Target string " +"pARTA"+" doesnt match any nodes on the network.");
//			   }
//			   else if (parties.size()>1)
//			   {
//		          throw new IllegalArgumentException("Target string " +"pARTA "+" matches multiple nodes on the network.");
//			   }
//			   
////			   Party registerBank,
////	           Party cbeBank, long chequeSerialNofrom, long chequeSerialNoTo, String accountNumber, long customerId,
////	           String customerName, long branchCode, String bankId, String chequeCurrency, long chequeBookSerialNo,
////	           UniqueIdentifier linearId)
//			   
//			   
//			   ChequeBookState state=new ChequeBookState(myIdentity,cbeIdentity,formBean.getChequeSerialNoFrom(),formBean.getChequeSerialNoTo(),formBean.getAccountNumber(),
//					   formBean.getCustomerId(),formBean.getCustomerName(),formBean.getBranchCode(),formBean.getBankId(),formBean.getChequeCurrency(),formBean.getChequeSerialNo(),new UniqueIdentifier());
//			   Response Responsestatus=null;
//		        try {
//		            final FlowHandle<SignedTransaction> flowHandle = proxy.startFlowDynamic(
//		            		ChequeBookRegisterationFlow.Initiator.class,
//		            		state, cbeIdentity );
//
//		            final SignedTransaction result2 = flowHandle.getReturnValue().get();
//		            final String msg = String.format("Transaction id %s committed to ledger.\n%s",
//		                    result2.getId(), result2.getTx().getOutputStates().get(0));
//		            Responsestatus=Response.status(CREATED).entity(msg).build();
//		        } catch (Exception e) {
//		        	Responsestatus= Response.status(BAD_REQUEST).entity(e.getMessage()).build();
//		        }
//			
//		        proxy.vaultQuery(ChequeBookState.class).getStates();
			returnPage= "RegConfirmation";	
	//	}
			/*else 
			{
				System.out.println("Bank id from view: "+formBean.getBankid());
				InputStream input = null;
				Properties prop=new Properties();
				
				try 
				{
					input=resourceLoader.getResource("classpath:bankconfig.properties").getInputStream();
					prop.load(input);
					System.out.println("prop get bank id: "+prop.getProperty(formBean.getBankid()));	
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				returnPage= "RegConfirmation";	
			}	*/			
		
//	  }
	  return returnPage;
	}
}