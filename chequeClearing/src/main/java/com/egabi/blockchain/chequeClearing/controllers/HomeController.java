package com.egabi.blockchain.chequeClearing.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import static java.lang.Math.toIntExact;

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

	@Autowired
	ResourceLoader resourceLoader;
	
	
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    
    @RequestMapping(value = "/{username}/RegConfirmation", method = RequestMethod.POST) 
	public String displayConfirmation(@PathVariable("username") String username, @ModelAttribute ChequeFormBean formBean,Model model)
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
    @RequestMapping(value = "/{username}/dashboard", method = RequestMethod.GET) 
    		public String displaydashboard(@PathVariable("username") String username, Model model) throws IOException
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
    			return "dashboard"; 
    		}

    @RequestMapping(value = "/{username}/RegSummary", method = RequestMethod.GET) 
	public String displaySummaryConfirmation(@PathVariable("username") String username,@ModelAttribute ChequeFormBean formBean,Model model)
	{
    	CordaCustomNodeServiceImpl partyA=  services.get(formBean.getBankId()+"NodeService");
    	partyA.registerChequeBook(formBean, formBean.getBankId());
    	
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

    
    @RequestMapping(value = "/{username}/search", method = RequestMethod.GET) 
 	public ModelAndView displaySearch(@PathVariable("username") String username,Model model)
	{
    	model.addAttribute("user" , username);
    	return new ModelAndView("search", "formBean", new ChequeFormBean());
	}
    
    @RequestMapping(value = "/{username}/ChequeDetailsSearch", method = RequestMethod.GET) 
 	public ModelAndView displayChequeDeatilsSearch(@PathVariable("username") String username,Model model)
	{
    	model.addAttribute("user", username);
    	return new ModelAndView("ChequeDetailsSearch", "formBean", new ChequeFormBean());
	}
    @RequestMapping(value = "/{username}/chequeSearchReport", method = RequestMethod.GET) 
    public ModelAndView displayChequeReport(@PathVariable("username") String username,Model model)
	{
    	model.addAttribute("user", username);
    	return new ModelAndView("chequeSearchReport", "formBean", new ChequeFormBean());
	}

    @RequestMapping(value = "/{username}/chequeSearchReportResult", method = RequestMethod.POST) 
	public ModelAndView displayChequeSearchReport(@PathVariable("username") String username,@RequestParam("chequeStatus") String chequeStatus, 
	@RequestParam("chequeSerialNo") Integer chequeSerialNo , ModelMap model, Model mv) throws NoSuchFieldException, SecurityException
	{
		ArrayList<ChequeDetail> cheques=new ArrayList<>();
    	if(chequeSerialNo!=0 && chequeSerialNo!=null)
    	{
    		System.out.println("Cheque SR no: "+chequeSerialNo);
	    	System.out.println("Cheque staus: "+chequeStatus);
			
    		if(!chequeStatus.equals("SELECT"))
    		{ 
    			//status is not null
    			cheques=ChequeDetailsSavingService.findOneWithSRnoAndStatus(chequeSerialNo, chequeStatus);
    		}
    		else
    		{
    			//status is null
    			cheques=ChequeDetailsSavingService.findOneWithSRno(chequeSerialNo);
    		}
    		for(int i=0;i<cheques.size();i++)
			{
				System.out.println("cheque no:"+i+" username: "+cheques.get(i).getPayToUsername());
			}
    	}
    	else
    	{
    		cheques=ChequeDetailsSavingService.findOneWithStatus(chequeStatus);
    	}
    	mv.addAttribute("retrievedCheques", cheques);
    	mv.addAttribute("user" , username);
    	return new ModelAndView("chequeSearchReportResult", "formBean", new ChequeFormBean());
	}
    
    @RequestMapping(value = "/{username}/chequeSearchReportResultDetails" , method = RequestMethod.GET)
    public ModelAndView displayChequeSearchReportResultDetails(@PathVariable("username") String username,@RequestParam("chequeSerialNo") long chequeSerialNo,
			Model model) throws NoSuchFieldException, SecurityException
	{
    	System.out.println("Cheque SR no: "+chequeSerialNo);
    	System.out.println("display ChequeSearchReportResultDetails page");
    	
    	boolean isVisiable=false;
    	
    	ChequeDetail cheque=ChequeDetailsSavingService.findOneChequeWithSRno(chequeSerialNo);
    	System.out.println("Cheque username: "+cheque.getPayToUsername());
    	
    	ChequeFormBean chequeBean=new ChequeFormBean();
    	
    	chequeBean.setChequeSerialNo(toIntExact(cheque.getChequeSrNo()));
    	chequeBean.setChequeAmount(cheque.getChequeAmount());
    	chequeBean.setChequeDueDate(new Date(cheque.getChequeDueDate().getTime()));
    	chequeBean.setPaytoUsername(cheque.getPayToUsername());
    	chequeBean.setAccountNumber(cheque.getAccountNo().toString());
    	chequeBean.setChequeCurrency(cheque.getChequeCurrency());
    	chequeBean.setBankId(cheque.getBankCode());
    	chequeBean.setChequeStatus(cheque.getStatus());
    	chequeBean.setCustomerName(cheque.getFromUsername());
    	
    	if(cheque.getIsCrossed().equals("Y"))	
    		chequeBean.setCrossed(true);
    	else
    		chequeBean.setCrossed(false);
    	
    	if(cheque.getStatus().equals("REVIEW REJECTED"))
    		isVisiable=true;
    		
    	System.out.println("Modify Visiable: "+isVisiable);
    	model.addAttribute("modifyVisiablity", isVisiable);
    	model.addAttribute("user",username);
    	return new ModelAndView("chequeSearchReportResultDetails", "formBean",chequeBean);
	}
    
    @RequestMapping(value = "/{username}/chequeDetailsEdit" , method = RequestMethod.POST)
    public ModelAndView displayChequeDetailsModification(@PathVariable("username") String username,
    @Valid @ModelAttribute("formBean") ChequeFormBean formBean, Model model) throws NoSuchFieldException, SecurityException
	{
    	System.out.println("chequeDetailsEdit chequeSerialNo: "+formBean.getChequeSerialNo());
    	model.addAttribute("user",username);
    	return new ModelAndView("chequeDetailsEdit", "formBean",formBean);
	}
    @RequestMapping(value = "/{username}/chequeDetailsEditSummary" , method = RequestMethod.POST)
    public ModelAndView displayChequeDetailsModificationSummary(@RequestParam("file") MultipartFile file,
    RedirectAttributes redirectAttributes , @PathVariable("username") String username, 
    @Valid @ModelAttribute("formBean") ChequeFormBean formBean , Model model)
    {
    	String crossedCheque="Y";
    	if(formBean.isCrossed()==false)
    		crossedCheque="N";
    	
    	ChequeDetailsSavingService.updateChequeById(formBean.getChequeDueDate(), 
    	formBean.getChequeSerialNo(), formBean.getChequeAmount(), crossedCheque, 
    	formBean.getPaytoUsername());
    	model.addAttribute("user",username);
    	return new ModelAndView("chequeDetailsEditSummary");
    }
    		
    
    @RequestMapping(value = "/{username}/ChequeDetailsSearchResult", method = RequestMethod.POST) 
	public ModelAndView displayChequeDetailsSearchResult(@PathVariable("username") String username, @RequestParam("chequeDueDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date chequeDueDate,@RequestParam("chequeStatus") String chequeStatus, 
	@RequestParam("chequeSerialNo") Integer chequeSerialNo , ModelMap model, Model mv) throws NoSuchFieldException, SecurityException
	{
    	String returnPage=null;
    	ChequeFormBean singleChequeFormBean = new ChequeFormBean();
		ArrayList<ChequeDetail> cheques=new ArrayList<>();
    	if(chequeSerialNo==null || chequeSerialNo==0)
    	{
    		System.out.println("Cheque SR no: "+chequeSerialNo);
	    	System.out.println("Cheque due date: "+chequeDueDate);
	    	System.out.println("Cheque status: "+chequeStatus);

    		if(!chequeStatus.equals("SELECT"))
    		{ 
    			//status is not null
    			if(chequeDueDate!=null)
    				cheques=ChequeDetailsSavingService.findOneWithStatusAndDuedate(chequeStatus, chequeDueDate);
    			else
    				cheques=ChequeDetailsSavingService.findOneWithStatus(chequeStatus);
    		}
    		else
    		{
    			//status is null
    			if(chequeDueDate!=null)
    				cheques=ChequeDetailsSavingService.findOneWithDuedate(chequeDueDate);
    		}
    	}
    	else
    	{
        	System.out.println("Cheque SR no: "+chequeSerialNo);
	    	System.out.println("Cheque due date: "+chequeDueDate);
	    	System.out.println("Cheque staus: "+chequeStatus);
			
    		if(!chequeStatus.equals("SELECT"))
    		{ 
    			//status is not null
    			if(chequeDueDate!=null)
    			{
    				cheques=ChequeDetailsSavingService.findOneWithSRnoAndStatusAndDuedate(chequeSerialNo, chequeStatus, chequeDueDate);
    			}
    			else
    				cheques=ChequeDetailsSavingService.findOneWithSRnoAndStatus(chequeSerialNo, chequeStatus);
    		}
    		else
    		{
    			//status is null
    			if(chequeDueDate!=null)
    				cheques=ChequeDetailsSavingService.findOneWithSRnoAndDuedate(chequeSerialNo,chequeDueDate);
    			
    			//if all params are null except sr no
    			else  
    				cheques=ChequeDetailsSavingService.findOneWithSRno(chequeSerialNo);
    		}		
    	}
    	for(int i=0;i<cheques.size();i++)
		{
			System.out.println("cheque no:"+i+" username: "+cheques.get(i).getPayToUsername());
		}
    	mv.addAttribute("retrievedCheques", cheques);
    	
    	mv.addAttribute("user" , username);
    	return new ModelAndView("ChequeDetailsSearchResult", "formBean", singleChequeFormBean);
	}
    
	@RequestMapping(value = "/{username}/chequesApproval", method = RequestMethod.GET) 
	public ModelAndView displayApprovalPage(@PathVariable("username") String username,@RequestParam("chequeSerialNo") long chequeSerialNo,
			Model model) throws NoSuchFieldException, SecurityException
	{
    	System.out.println("Cheque SR no: "+chequeSerialNo);
    	System.out.println("display approval page");
    	
    	ChequeDetail cheque=ChequeDetailsSavingService.findOneChequeWithSRno(chequeSerialNo);
    	System.out.println("Cheque username: "+cheque.getPayToUsername());
    	
    	ChequeFormBean chequeBean=new ChequeFormBean();
    	
    	chequeBean.setChequeSerialNo(toIntExact(cheque.getChequeSrNo()));
    	chequeBean.setChequeAmount(cheque.getChequeAmount());
    	chequeBean.setChequeDueDate(new Date(cheque.getChequeDueDate().getTime()));
    	chequeBean.setPaytoUsername(cheque.getPayToUsername());
    	chequeBean.setAccountNumber(cheque.getAccountNo().toString());
    	chequeBean.setChequeCurrency(cheque.getChequeCurrency());
    	chequeBean.setBankId(cheque.getBankCode());
    	chequeBean.setChequeStatus(cheque.getStatus());
    	chequeBean.setCustomerName(cheque.getFromUsername());
    	
    	if(cheque.getIsCrossed().equals("Y"))	
    		chequeBean.setCrossed(true);
    	else
    		chequeBean.setCrossed(false);
    	
    	model.addAttribute("user" , username);
    	return new ModelAndView("chequesApproval", "formBean",chequeBean);
	}
	@RequestMapping(value = "/{username}/approvalSummary", method = RequestMethod.POST) 
	public ModelAndView displayApprovalSummary(@PathVariable("username") String username,
	@RequestParam("chequeSerialNo") long chequeSerialNo ,Model model) throws NoSuchFieldException, SecurityException
	{
    	System.out.println("Cheque SR no: "+chequeSerialNo);
    	ChequeDetailsSavingService.setUserInfoById("REVIEW APPROVED", chequeSerialNo);
    	model.addAttribute("user",username);
    	return new ModelAndView("approvalSummary", "formBean",new ChequeFormBean());
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
    	System.out.println("Cheque account no: "+accNo);
    	
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
		
		model.addAttribute("user" , username);
		   
		CordaCustomNodeServiceImpl PartyA=  services.get(bankid+"NodeService");
		 
		
		singleChequeFormBean=PartyA.retrieveChequeBook(bankid,accNo,serialno);
	
		
		return  new ModelAndView("SearchResult", "formBean", singleChequeFormBean);
	}
    

	@ModelAttribute("formBean")
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}
	
    @RequestMapping(value = "/{username}/submittingSummary", method = RequestMethod.POST) 
	public String chequeSubmittingSummary(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes  , @PathVariable("username") String username ,
            @Valid @ModelAttribute("formBean")	ChequeFormBean  formBean, Model model)
	{
    	System.out.println("submitting summary hello");
    	System.out.println("submitting summary: "+formBean.getPaytoUsername());
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
		submittedCheque.setPayToUsername(formBean.getPaytoUsername());
		submittedCheque.setFromUsername(formBean.getCustomerName());
		submittedCheque.setStatus("PENDING REVIEW");
		
		ChequeDetailsSavingService.saveCheque(submittedCheque);
		
		
		storageService.store(file, username);
        
        
    	return "submittingSummary"; 
	}
}
