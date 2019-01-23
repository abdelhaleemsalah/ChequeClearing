package com.egabi.blockchain.chequeClearing.controllers;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.ChequeDetailsSavingService;
import com.egabi.blockchain.chequeClearing.services.StorageService;
import net.corda.core.contracts.UniqueIdentifier;
import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
//import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
//import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodesController;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.beans.RpcPermissionRepository;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.beans.RpcRoleRepository;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.beans.RpcUserRepository;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.entities.RpcRole;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.entities.RpcUser;
import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import com.template.flow.ChequeBookRegisterationFlow;
import com.template.state.ChequeBookState;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import javax.ws.rs.core.Response;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.messaging.FlowHandle;
import net.corda.core.transactions.SignedTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@SessionAttributes("formBean")
public class HomeController  {

//	  @Autowired
//	    private CordaNetworkService networkService;
	    // Autowire all created node services directly, mapped by name
//	    @Autowired
//	    private Map<String, CordaNodeService> services;
//	    // Autowire a node-specific service
//	    @Autowired
//	    @Qualifier("partyANodeService")
//	    private CordaNodeService service;
	    // You can also specify a custom type explicitly
	    // for nodes configured using the  `primaryServiceType`
	    // application property (see following section)
	   // @Autowired
	   // private SampleCustomCordaNodeServiceImpl customCervice;
	
	
	  @Autowired
	    private Map<String, CordaNodeService> services;
	   
	  @Autowired
	  @Qualifier("HSBCRpcConnection")
	  private NodeRpcConnection rpcConnection ;
	   
    @Autowired
    StorageService storageService;
    
    @Autowired
    private ChequeBookSavingService chequeBookSavingService;
    
    @Autowired
    private ChequeDetailsSavingService ChequeDetailsSavingService;

//    @Autowired
//    private CordaNodesController corderController;
	@Autowired
	ResourceLoader resourceLoader;
	
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    
   // @PostMapping("/RegConfirmation") 
    @RequestMapping(value = "/RegConfirmation", method = RequestMethod.POST) 
	public String displayConfirmation( @ModelAttribute ChequeFormBean formBean,Model model)
	{
    	model.addAttribute("formBean", formBean);
    	return "RegConfirmation"; 
	}
    
    @RequestMapping(value = "/RegSummary", method = RequestMethod.GET) 
	public String displaySummaryConfirmation(@ModelAttribute ChequeFormBean formBean,Model model)
	{
    	
    	;
		   CordaNodeService PartyA=  services.get(formBean.getBankId()+"NodeService");
		   
		   PartyA.peerNames();
		// 
		   CordaRPCOps proxy= rpcConnection.getProxy();		   
		   Set<Party> parties= proxy.partiesFromName(formBean.getBankId(),  true);
		   Set<Party> cbeParty= proxy.partiesFromName("CBC",  true);
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
		   
//		   Party registerBank,
//           Party cbeBank, long chequeSerialNofrom, long chequeSerialNoTo, String accountNumber, long customerId,
//           String customerName, long branchCode, String bankId, String chequeCurrency, long chequeBookSerialNo,
//           UniqueIdentifier linearId)
		   
		   
		   ChequeBookState state=new ChequeBookState(myIdentity,cbeIdentity,formBean.getChequeSerialNoFrom(),formBean.getChequeSerialNoTo(),formBean.getAccountNumber(),
				   formBean.getCustomerId(),formBean.getCustomerName(),formBean.getBranchCode(),formBean.getBankId(),formBean.getChequeCurrency(),formBean.getChequeSerialNo(),new UniqueIdentifier());
		   Response Responsestatus=null;
	        try {
	            final FlowHandle<SignedTransaction> flowHandle = proxy.startFlowDynamic(
	            		ChequeBookRegisterationFlow.Initiator.class,
	            		state, cbeParty, true
	            );

	            final SignedTransaction result = flowHandle.getReturnValue().get();
	            final String msg = String.format("Transaction id %s committed to ledger.\n%s",
	                    result.getId(), result.getTx().getOutputStates().get(0));
	            Responsestatus=Response.status(CREATED).entity(msg).build();
	        } catch (Exception e) {
	        	Responsestatus= Response.status(BAD_REQUEST).entity(e.getMessage()).build();
	        }
		   
    	
    	
		
		return "RegSummary"; 
	}
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String displayLogin( Model model)
	{
		model.addAttribute("formBean", new ChequeFormBean());
		return "hello"; 
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET) 
	public String displayHelloLogin( Model model)
	{
		if(!model.containsAttribute("formBean"))
		{
		model.addAttribute("formBean", new ChequeFormBean());
		}
		
		return "hello"; 
	}
    @GetMapping("/{tenant}/home")
    public String homePage(Model model , @PathVariable("tenant") String merchant)
    {
        model.addAttribute("files", storageService.loadAll(merchant).map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile",  path.getFileName().toString() , merchant).build().toString())
                .collect(Collectors.toList()));

        model.addAttribute("merchant" , merchant);

        return "upload";
    }

    
    @RequestMapping(value = "/search", method = RequestMethod.GET) 
 	public ModelAndView  displaySearch()
	{
    	return new ModelAndView("search", "formBean", new ChequeFormBean());
 
	}
    
//    @Autowired private RpcUserRepository rpcUserRepository;
//    @Autowired private RpcPermissionRepository rpcPermissionRepository;
//    @Autowired private RpcRoleRepository rpcRoleRepository;

    // Use repos in your code, e.g.
 
     
    @RequestMapping(value = "/SearchResult", method = RequestMethod.POST) 
	public ModelAndView displaySearchResult(@RequestParam("chequeserialNO") long serialno, 
	@RequestParam("bankid") String bankid, @RequestParam("accountnumber") long accNo,
	ModelMap model)
	{
    	String returnPage=null;
    	ChequeFormBean singleChequeFormBean = new ChequeFormBean();
    	System.out.println("Cheque SR no: "+serialno);
    	System.out.println("Cheque bank id: "+bankid);
    	System.out.println("Cheque bank id: "+accNo);
    	
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
		
		if(bankid.equals(propBankId))
		{
	    	ChequeBookDetail chequebook=chequeBookSavingService.findOneWithSRnoAndAccNo(serialno, accNo);
	    	System.out.println("Cheque customer name: "+chequebook.getCustomerName());
	    	
	    	if(chequebook!=null)
	    	{
		    	singleChequeFormBean.setChequeSerialNo(serialno);
		    	singleChequeFormBean.setCustomerName(chequebook.getCustomerName());
		    	singleChequeFormBean.setAccountNumber(String.valueOf(chequebook.getAccountId()));
		    	singleChequeFormBean.setChequeCurrency(chequebook.getCurrency());
		    	singleChequeFormBean.setBankId(String.valueOf(chequebook.getBankCode()));
		    	singleChequeFormBean.setBranchCode(chequebook.getBranchId());
		    	model.addAttribute("formBean",singleChequeFormBean);
		    	returnPage= "SearchResult";
	    	}
		}
		else
		{
			//call block chain
			System.out.println("Bank is not CIB");
			
//			java.util.List<RpcRole> roles=new ArrayList<>();
//			   RpcUser user = rpcUserRepository.save(new RpcUser("user1", "password",  roles));
//			
			

			
			
	
			   
			   
			   
			   
//			   proxy.startFlowDynamic(arg0, arg1).
//			
			returnPage= "SearchResult";
		}
		
		

		return  new ModelAndView(returnPage, "formBean", singleChequeFormBean);
	}
    

	@ModelAttribute("formBean")
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}
	
    @RequestMapping(value = "/submittingSummary", method = RequestMethod.GET) 
	public String chequeSubmittingSummary(@Valid @ModelAttribute("formBean")	ChequeFormBean  formBean  , Model model)
	{
    	System.out.println("model.toString() "+model.containsAttribute("formBean"));
    	System.out.println("model.toString() "+model.toString());
    	System.out.println("submitting summary hello");
    	System.out.println("submitting summary: "+formBean.getCustomerName());
		ChequeDetail submittedCheque=new ChequeDetail();
		
    	if(formBean.isCrossed()==false)
    		submittedCheque.setIsCrossed("N");
    	else
    		submittedCheque.setIsCrossed("Y");
    		
		submittedCheque.setAccountNo(formBean.getAccountNumber());
		submittedCheque.setBankCode(formBean.getBankId());
		submittedCheque.setBranchId(formBean.getBranchCode());
		submittedCheque.setChequeAmount(formBean.getChequeAmount());
		submittedCheque.setChequeBookId(formBean.getChequeBookSerialNo());
		submittedCheque.setChequeCurrency(formBean.getChequeCurrency());
		submittedCheque.setChequeDueDate(new Timestamp(formBean.getChequeDueDate().getTime()));
		submittedCheque.setChequeSrNo(formBean.getChequeSerialNo());
		submittedCheque.setPayToUsername(formBean.getCustomerName());
		submittedCheque.setStatus("SUBMITTED");
		
		ChequeDetailsSavingService.saveCheque(submittedCheque);
		
    	return "submittingSummary"; 
	}
    
    /*@RequestMapping(value = "/SearchResult", method = RequestMethod.POST) 
	public String displaySearchResult(@RequestParam("chequeSRno") long serialno, 
	@RequestParam("bankid") String bankid, @RequestParam("accNo") long accNo,
	Model model)
	{}*/
}
