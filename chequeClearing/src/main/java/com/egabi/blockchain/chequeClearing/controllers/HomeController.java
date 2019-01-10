package com.egabi.blockchain.chequeClearing.controllers;

import com.egabi.blockchain.chequeClearing.entities.ChequeBookDetail;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    StorageService storageService;
    
    @Autowired
    private ChequeBookSavingService chequeBookSavingService;

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
	public String displaySearchResult(@RequestParam("chequeSRno") long serialno, Model model)
	{
    	FormBean formBean = new FormBean();
    	System.out.println("Cheque SR no: "+serialno);
    	ChequeBookDetail chequebook=new ChequeBookDetail();
    	chequebook=chequeBookSavingService.selectChequeBySerial(serialno);
    	System.out.println("Chequebook currency: "+chequebook.getCurrency());
    	if((serialno >= chequebook.getChequeSrNoFrom()) && 
    	   (serialno <= chequebook.getChequeSrNoTo())){	
    		formBean.setCustomername(chequebook.getCustomerName());
    		formBean.setAccountnumber(chequebook.getAccountId().toString());
    		formBean.setChequecurrency(chequebook.getCurrency());
    		model.addAttribute("formBean",formBean);
    		return "/SearchResult";
    	}
    	return null;
	}
}
