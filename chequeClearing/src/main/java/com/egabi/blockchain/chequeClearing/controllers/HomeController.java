package com.egabi.blockchain.chequeClearing.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.ChequeDetailsSavingService;
import com.egabi.blockchain.chequeClearing.services.CordaCustomNodeServiceImpl;
import com.egabi.blockchain.chequeClearing.services.StorageService;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import kotlin.Suppress;

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
	
	

    
	private String defaultNodeName;
	  @Autowired
	   @Suppress(names="SpringJavaInjectionPointsAutowiringInspection")
	   private  Map<String,CordaCustomNodeServiceImpl> services;
	   
	  @Autowired
	  @Qualifier("HSBCRpcConnection")
	  private NodeRpcConnection hsbcRpcConnection ;
	   
	  @Autowired
	  @Qualifier("HSBCNodeService")
	  private CordaNodeService hsbcNodeService ;
	   
	  
	  
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
    
    
    
    @PostConstruct
    public void postConstruct()
    {
    	if (services.keySet().size() == 1) 
    		{defaultNodeName=services.keySet().iterator().next();
    		}
    	else 
    		{
    		
    		}
    		
    }
    @RequestMapping(value = "/RegSummary", method = RequestMethod.GET) 
	public String displaySummaryConfirmation(@ModelAttribute ChequeFormBean formBean,Model model)
	{
    	
    	;
    	CordaCustomNodeServiceImpl PartyA=  services.get(formBean.getBankId()+"NodeService");
		 
		   
		// 
		   PartyA.registerChequeBook(formBean, formBean.getBankId());
//		   CordaRPCOps proxy= rpcConnection.getProxy();		   
//		   Set<Party> parties= proxy.partiesFromName(formBean.getBankId(),  true);
//		   Set<Party> cbeParty= proxy.partiesFromName("CBC",  true);
//		   final Party myIdentity = parties.iterator().next();
//		   final Party cbeIdentity = cbeParty.iterator().next();
//		   if(parties.isEmpty())
//		   {
//			  throw new IllegalArgumentException("Target string " +"pARTA"+" doesnt match any nodes on the network.");
//		   }
//		   else if (parties.size()>1)
//		   {
//	          throw new IllegalArgumentException("Target string " +"pARTA "+" matches multiple nodes on the network.");
//		   }
//		   
////		   Party registerBank,
////           Party cbeBank, long chequeSerialNofrom, long chequeSerialNoTo, String accountNumber, long customerId,
////           String customerName, long branchCode, String bankId, String chequeCurrency, long chequeBookSerialNo,
////           UniqueIdentifier linearId)
//		   
//		   
//		   ChequeBookState state=new ChequeBookState(myIdentity,cbeIdentity,formBean.getChequeSerialNoFrom(),formBean.getChequeSerialNoTo(),formBean.getAccountNumber(),
//				   formBean.getCustomerId(),formBean.getCustomerName(),formBean.getBranchCode(),formBean.getBankId(),formBean.getChequeCurrency(),formBean.getChequeSerialNo(),new UniqueIdentifier());
//		   Response Responsestatus=null;
//	        try {
//	            final FlowHandle<SignedTransaction> flowHandle = proxy.startFlowDynamic(
//	            		ChequeBookRegisterationFlow.Initiator.class,
//	            		state, cbeParty, true
//	            );
//
//	            final SignedTransaction result = flowHandle.getReturnValue().get();
//	            final String msg = String.format("Transaction id %s committed to ledger.\n%s",
//	                    result.getId(), result.getTx().getOutputStates().get(0));
//	            Responsestatus=Response.status(CREATED).entity(msg).build();
//	        } catch (Exception e) {
//	        	Responsestatus= Response.status(BAD_REQUEST).entity(e.getMessage()).build();
//	        }
//		   
    	
    	
		
		return "RegSummary"; 
	}
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String displayLogin( Model model)
	{
		model.addAttribute("formBean", new ChequeFormBean());
		return "Registeration"; 
	}
	@RequestMapping(value = "/{username}/Registeration", method = RequestMethod.GET) 
	public String displayHelloLogin(@PathVariable("username") String username, Model model) throws IOException
	{
		if(!model.containsAttribute("formBean"))
		{
	       
		model.addAttribute("formBean", new ChequeFormBean());
		 model.addAttribute("user" , username);
		}
		 model.addAttribute("files", storageService.loadAll(username).map(
		          path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
		                  "serveFile",  path.getFileName().toString() , username).build().toString())
		          .collect(Collectors.toList()));
		return "Registeration"; 
	}
//    @GetMapping("/{tenant}/home")
//    public String homePage(Model model , @PathVariable("tenant") String merchant)
//    {
//        model.addAttribute("files", storageService.loadAll(merchant).map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile",  path.getFileName().toString() , merchant).build().toString())
//                .collect(Collectors.toList()));
//
//        model.addAttribute("merchant" , merchant);
//
//        return "upload";
//    }

    
    @RequestMapping(value = "/{username}/search", method = RequestMethod.GET) 
 	public ModelAndView  displaySearch(@PathVariable("username") String username,Model model)
	{
    	model.addAttribute("user" , username);
    	return new ModelAndView("search", "formBean", new ChequeFormBean());
	}
    
    @RequestMapping(value = "/{username}/ChequeDetailsSearch", method = RequestMethod.GET) 
 	public ModelAndView displayChequeDeatilsSearch(@PathVariable("username") String username,Model model)
	{
    	model.addAttribute("user" , username);
    	return new ModelAndView("ChequeDetailsSearch", "formBean", new ChequeFormBean());
	}

    // Use repos in your code, e.g.
    
    @RequestMapping(value = "/{username}/ChequeDetailsSearchResult", method = RequestMethod.POST) 
	public ModelAndView displayChequeDetailsSearchResult(@RequestParam("chequeDueDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date chequeDueDate,@RequestParam("chequeStatus") String chequeStatus, 
	@RequestParam("chequeSerialNo") Integer chequeSerialNo , ModelMap model) throws NoSuchFieldException, SecurityException
	{
    	String returnPage=null;
    	ChequeFormBean singleChequeFormBean = new ChequeFormBean();
    	System.out.println("Cheque SR no: "+chequeSerialNo);
    	System.out.println("Cheque due date: "+chequeDueDate);
    	System.out.println("Cheque staus: "+chequeStatus);
    	
    	ChequeDetail RetrievedCheque=new ChequeDetail();
    	RetrievedCheque=ChequeDetailsSavingService.findOneWithSRnoAndStatusAndDuedate(chequeSerialNo, chequeStatus, chequeDueDate);

    	System.out.println("cheque username: "+RetrievedCheque.getPayToUsername());
    	
		return  new ModelAndView("ChequeDetailsSearchResult", "formBean", singleChequeFormBean);
	
	}
    
    
    
    
    
    
    
    
    
 
     
    @RequestMapping(value = "/{username}/SearchResult", method = RequestMethod.POST) 
	public ModelAndView displaySearchResult(@PathVariable("username") String username,@RequestParam("chequeSerialNo") Integer serialno, 
	@RequestParam("bankId") String bankid, @RequestParam("accountNumber") long accNo,
	ModelMap model) throws NoSuchFieldException, SecurityException
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
		
		
		
		
//		if(bankid.equals(propBankId))
//		{
//	    	ChequeBookDetail chequebook=chequeBookSavingService.findOneWithSRnoAndAccNo(serialno, accNo);
//	    	System.out.println("Cheque customer name: "+chequebook.getCustomerName());
//	    	
//	    	if(chequebook!=null)
//	    	{
//		    	singleChequeFormBean.setChequeSerialNo(serialno);
//		    	singleChequeFormBean.setCustomerName(chequebook.getCustomerName());
//		    	singleChequeFormBean.setAccountNumber(String.valueOf(chequebook.getAccountId()));
//		    	singleChequeFormBean.setChequeCurrency(chequebook.getCurrency());
//		    	singleChequeFormBean.setBankId(String.valueOf(chequebook.getBankCode()));
//		    	singleChequeFormBean.setBranchCode(chequebook.getBranchId());
//		    	model.addAttribute("formBean",singleChequeFormBean);
//		    	returnPage= "SearchResult";
//	    	}
//		}
//		else
//		{
//			//call block chain
//			System.out.println("Bank is not CIB");
//			
////			java.util.List<RpcRole> roles=new ArrayList<>();
////			   RpcUser user = rpcUserRepository.save(new RpcUser("user1", "password",  roles));
////			
//			
//
//			
//			
//	
//			   
//			   
//			   
//			   
////			   proxy.startFlowDynamic(arg0, arg1).
////			
//			returnPage= "SearchResult";
//		}
		
		CordaCustomNodeServiceImpl PartyA=  services.get(bankid+"NodeService");
		 
		   
		// 
		singleChequeFormBean=PartyA.retrieveChequeBook(bankid,accNo,serialno);
		
//		retrieveChequeBook
//		
//		
//		   CordaRPCOps proxy= hsbcRpcConnection.getProxy();	
//		   Set<Party> parties= proxy.partiesFromName(bankid,  true);
//		    final Party myIdentity = parties.iterator().next();
//		   
//		    Stream<StateAndRef<ChequeBookState>> statesAndRefs=proxy.vaultQuery(ChequeBookState.class).getStates().stream()
//           .filter(it -> it.getState().getData().getRegisterBank().equals(myIdentity));
//		   
//		    
//		    QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
//		    Field registerBank = IOUSchemaV1.PersistentIOU.class.getDeclaredField("registerBank");
//	        CriteriaExpression registerBankIndex = Builder.equal(registerBank, myIdentity.getName().toString());
//	        QueryCriteria lenderCriteria = new QueryCriteria.VaultCustomQueryCriteria(registerBankIndex);
//	        QueryCriteria criteria = generalCriteria.and(lenderCriteria);
//	        List<StateAndRef<ChequeBookState>> results = proxy.vaultQueryByCriteria(criteria,ChequeBookState.class).getStates();
//	        
//	        for(StateAndRef<ChequeBookState> chequeBook:results)
//	        {
//	        	if( chequeBook.getState().getData().getAccountNumber().equals(String.valueOf(accNo)))
//		    	{
//		    		if(chequeBook.getState().getData().getChequeSerialNofrom()<=serialno &&chequeBook.getState().getData().getChequeSerialNoTo()>=serialno  )
//		    		{
//		    			singleChequeFormBean.setChequeSerialNo(serialno);
//				    	singleChequeFormBean.setCustomerName(chequeBook.getState().getData().getCustomerName());
//				    	singleChequeFormBean.setAccountNumber(String.valueOf(chequeBook.getState().getData().getAccountNumber()));
//				    	singleChequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency());
//				    	singleChequeFormBean.setBankId(String.valueOf(chequeBook.getState().getData().getBankId()));
//				    	singleChequeFormBean.setBranchCode(chequeBook.getState().getData().getBranchCode());
//				    	model.addAttribute("formBean",singleChequeFormBean);
//		    		}
//		    	}
//	        }
	        
//		
//		    statesAndRefs.forEach(item->{
//		    	StateAndRef<ChequeBookState> chequeBook=item;
//		    	if( chequeBook.getState().getData().getAccountNumber().equals(accNo))
//		    	{
//		    		if(chequeBook.getState().getData().getChequeSerialNofrom()>=serialno &&chequeBook.getState().getData().getChequeSerialNoTo()>=serialno  )
//		    		{
//		    			singleChequeFormBean.setChequeSerialNo(serialno);
//				    	singleChequeFormBean.setCustomerName(chequeBook.getState().getData().getCustomerName());
//				    	singleChequeFormBean.setAccountNumber(String.valueOf(chequeBook.getState().getData().getAccountNumber()));
//				    	singleChequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency());
//				    	singleChequeFormBean.setBankId(String.valueOf(chequeBook.getState().getData().getBankId()));
//				    	singleChequeFormBean.setBranchCode(chequeBook.getState().getData().getBranchCode());
//				    	model.addAttribute("formBean",singleChequeFormBean);
//		    		}
//		    	}
//		    }
//		    	
//		
//			);
		    
//		    	StateAndRef<ChequeBookState> chequeBook=statesAndRefs.iterator().next();
//		    	if( chequeBook.getState().getData().getAccountNumber().equals(accNo))
//		    	{
//		    		if(chequeBook.getState().getData().getChequeSerialNofrom()>=serialno &&chequeBook.getState().getData().getChequeSerialNoTo()>=serialno  )
//		    		{
//		    			singleChequeFormBean.setChequeSerialNo(serialno);
//				    	singleChequeFormBean.setCustomerName(chequeBook.getState().getData().getCustomerName());
//				    	singleChequeFormBean.setAccountNumber(String.valueOf(chequeBook.getState().getData().getAccountNumber()));
//				    	singleChequeFormBean.setChequeCurrency(chequeBook.getState().getData().getChequeCurrency());
//				    	singleChequeFormBean.setBankId(String.valueOf(chequeBook.getState().getData().getBankId()));
//				    	singleChequeFormBean.setBranchCode(chequeBook.getState().getData().getBranchCode());
//				    	model.addAttribute("formBean",singleChequeFormBean);
//		    		}
//		    	}
//		    	);
//		    
		
	    	model.addAttribute("user" , username);
		   
		return  new ModelAndView("SearchResult", "formBean", singleChequeFormBean);
	}
    

	@ModelAttribute("formBean")
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}
	
	
    @PostMapping("/{merchant}/upload")
//   public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                 RedirectAttributes redirectAttributes  , @PathVariable("merchant") String merchant )
//                throws IllegalStateException, IOException {
//
//
//        java.io.File convFile = new File(file.getOriginalFilename()).getAbsoluteFile();
//        storageService.store(file, merchant);
//        //file.transferTo(convFile);
//
//      //  gateway.sendToFtp(convFile,merchant);
//
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/{merchant}/home";
//    }
	
	
    @RequestMapping(value = "/{username}/submittingSummary", method = RequestMethod.POST) 
	public String chequeSubmittingSummary(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes  , @PathVariable("username") String username ,@Valid @ModelAttribute("formBean")	ChequeFormBean  formBean  , Model model)
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
		submittedCheque.setChequeAmount(formBean.getChequeAmount());
		submittedCheque.setChequeBookId(formBean.getChequeBookSerialNo());
		submittedCheque.setChequeCurrency(formBean.getChequeCurrency());
		submittedCheque.setChequeDueDate(new Timestamp(formBean.getChequeDueDate().getTime()));
		submittedCheque.setChequeSrNo(formBean.getChequeSerialNo());
		submittedCheque.setPayToUsername(formBean.getCustomerName());
		submittedCheque.setStatus("PENDING REVIEW");
		
		ChequeDetailsSavingService.saveCheque(submittedCheque);
		
		
		//java.io.File convFile = new File(file.getOriginalFilename()).getAbsoluteFile();
        storageService.store(file, username);
        
        
    	return "submittingSummary"; 
	}
}
