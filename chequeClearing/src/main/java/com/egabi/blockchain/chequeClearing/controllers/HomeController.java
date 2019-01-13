package com.egabi.blockchain.chequeClearing.controllers;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.repositories.ChequebookRepository;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    StorageService storageService;
    
    @Autowired
    private ChequeBookSavingService chequeBookSavingService;

	@Autowired
	ResourceLoader resourceLoader;
	
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    
   // @PostMapping("/RegConfirmation") 
    @RequestMapping(value = "/RegConfirmation", method = RequestMethod.GET) 
	public String displayConfirmation( @ModelAttribute FormBean formBean,Model model)
	{
    	model.addAttribute("formBean", formBean);
    	return "RegConfirmation"; 
	}
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String displayLogin( Model model)
	{
		model.addAttribute("formBean", new FormBean());
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
	public String displayConfirmation()
	{
    	return "search"; 
	}
    
    @RequestMapping(value = "/SearchResult", method = RequestMethod.POST) 
	public String displaySearchResult(@RequestParam("chequeSRno") long serialno, 
	@RequestParam("bankid") String bankid, @RequestParam("accNo") long accNo,
	Model model)
	{
    	FormBean formBean = new FormBean();
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
	    	
	    	
	    	formBean.setCustomername(chequebook.getCustomerName());
	    	formBean.setAccountnumber(String.valueOf(chequebook.getAccountId()));
	    	formBean.setChequecurrency(chequebook.getCurrency());
	    	model.addAttribute("formBean",formBean);
	    	return "/SearchResult";
		}
		else
		{
			//call block chain
			System.out.println("Bank is not CIB");
			return "/SearchResult";
		}
	}
}
