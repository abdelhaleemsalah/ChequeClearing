package com.egabi.blockchain.chequeClearing.controllers;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.entities.ChequeDetail;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.ChequeDetailsSavingService;
import com.egabi.blockchain.chequeClearing.services.StorageService;
//import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
//import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodesController;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.beans.RpcPermissionRepository;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.beans.RpcRoleRepository;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.beans.RpcUserRepository;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.entities.RpcRole;
//import com.github.manosbatsis.corbeans.spring.boot.corda.rpc.entities.RpcUser;
import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;

import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;

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
	   
//	  @Autowired
//	  private NodeRpcConnection rpcConnection ;
	   
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
	public String displaySummaryConfirmation(Model model)
	{
		
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
		    	singleChequeFormBean.setChequeserialNO(serialno);
		    	singleChequeFormBean.setCustomername(chequebook.getCustomerName());
		    	singleChequeFormBean.setAccountnumber(String.valueOf(chequebook.getAccountId()));
		    	singleChequeFormBean.setChequecurrency(chequebook.getCurrency());
		    	singleChequeFormBean.setBankid(String.valueOf(chequebook.getBankCode()));
		    	singleChequeFormBean.setBranchcode(chequebook.getBranchId());
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
			   CordaNodeService PartyA=  services.get(bankid+"NodeService");
			   PartyA.peerNames();
			// 
//			   CordaRPCOps proxy= rpcConnection.getProxy();		   
//			   Set<Party> parties= proxy.partiesFromName(bankid,  true);
//			   if(parties.isEmpty())
//			   {
//				  throw new IllegalArgumentException("Target string " +"pARTA"+" doesnt match any nodes on the network.");
//			   }
//			   else if (parties.size()>1)
//			   {
//		          throw new IllegalArgumentException("Target string " +"pARTA "+" matches multiple nodes on the network.");
//			   }
			   
			   
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
    	System.out.println("submitting summary: "+formBean.getCustomername());
		ChequeDetail submittedCheque=new ChequeDetail();
		
    	if(formBean.getIsCrossed()==false)
    		submittedCheque.setIsCrossed("N");
    	else
    		submittedCheque.setIsCrossed("Y");
    		
		submittedCheque.setAccountNo(formBean.getAccountnumber());
		submittedCheque.setBankCode(formBean.getBankid());
		submittedCheque.setBranchId(formBean.getBranchcode());
		submittedCheque.setChequeAmount(formBean.getChequeAmount());
		submittedCheque.setChequeBookId(formBean.getChequebookserialNO());
		submittedCheque.setChequeCurrency(formBean.getChequecurrency());
		submittedCheque.setChequeDueDate(new Timestamp(formBean.getChequeDueDate().getTime()));
		submittedCheque.setChequeSrNo(formBean.getChequeserialNO());
		submittedCheque.setPayToUsername(formBean.getCustomername());
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
