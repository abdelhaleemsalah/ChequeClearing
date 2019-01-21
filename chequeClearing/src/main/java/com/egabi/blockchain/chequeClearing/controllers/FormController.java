package com.egabi.blockchain.chequeClearing.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/hello")
@SessionAttributes("formBean")
//@PropertySource("classpath:resources/bankconfig.properties")
public class FormController {

	// Invoked on every request

	@Autowired
	ResourceLoader resourceLoader;
	
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
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}
	
	@PostMapping
	public String processSubmit(@Valid 	ChequeFormBean  formBean, BindingResult result, 
								@ModelAttribute("ajaxRequest") boolean ajaxRequest, 
								Model model) {
		
		
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
		System.out.println("account id: "+formBean.getAccountnumber());
		
		if (result.hasErrors()) {
			System.out.println("pageName has error binding            ");
			return null;
		}
		//model.addAttribute("formBean",formBean);
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		System.out.println("account number "+formBean.getAccountnumber());
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