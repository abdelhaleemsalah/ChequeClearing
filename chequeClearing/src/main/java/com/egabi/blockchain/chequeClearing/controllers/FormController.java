package com.egabi.blockchain.chequeClearing.controllers;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/hello")
@SessionAttributes("formBean")
public class FormController {

	// Invoked on every request

	
	@Autowired
    private ChequeBookSavingService chequeBookSavingService;
	
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)

	
//	 @RequestMapping(value = "/hello", method = RequestMethod.GET)
//	   public ModelAndView hello() {
//	      return new ModelAndView("hello", "formBean", new FormBean());
//	   }
	@ModelAttribute("formBean")
	public FormBean createFormBean() {
		return new FormBean();
	}


	
	@PostMapping
	public String processSubmit(@RequestParam(value="pageName") String page,@Valid FormBean formBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model) {
		
		
		System.out.println("inside processSubmit");
		System.out.println("pageName            "+page);
		String returnPage=null;
		if(page.equalsIgnoreCase("RegConfirmation"))
		{
			returnPage= "hello";
		}
		else if(page.equalsIgnoreCase("hello"))
		{
		
		if (result.hasErrors()) {
			return null;
		}
		//model.addAttribute("formBean",formBean);
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		System.out.println("account number "+formBean.getAccountnumber());
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			//redirectAttrs.addFlashAttribute("message", message);
			System.out.println("inside process submit");
			/*FormBean formBean1= new FormBean();
			formBean1.setAccountnumber("111111");
			formBean1.setBankid("CIB");
			formBean1.setChequecurrency("EGP");
			formBean1.setBranchcode(new BigDecimal(104));
			formBean1.setChequeserialNOfrom(new BigDecimal(5));
			formBean1.setChequeserialNOto(new BigDecimal(20));
			formBean1.setCustomerid(new BigDecimal(578369));
			formBean1.setCustomername("hhhhhhh");*/
			
			ChequeBookDetail newCreatedChequeBook=new ChequeBookDetail();
			newCreatedChequeBook.setAccountId(new BigDecimal(formBean.getAccountnumber()));
			newCreatedChequeBook.setBankCode(formBean.getBankid());
			newCreatedChequeBook.setBranchId(new BigDecimal(77));
			newCreatedChequeBook.setChequeBookId(new BigDecimal(2));
			newCreatedChequeBook.setChequeSrNoFrom(formBean.getChequeserialNOfrom());
			newCreatedChequeBook.setChequeSrNoTo(formBean.getChequeserialNOto());
			newCreatedChequeBook.setCustomerName(formBean.getCustomername());
			newCreatedChequeBook.setCurrency(formBean.getChequecurrency());
			
			
			chequeBookSavingService.saveChequeBook(newCreatedChequeBook);
			
			model.addAttribute("formBean",formBean);
			returnPage= "RegConfirmation";	
				
		}
		}
		return returnPage;
	}
	
}