package com.egabi.blockchain.chequeClearing.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.CordaCustomNodeServiceImpl;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import kotlin.Suppress;

@Controller
@RequestMapping("/{username}/Registeration")
@SessionAttributes("formBean")
public class FormController {

	// Invoked on every request

	@Autowired
	@Suppress(names = "SpringJavaInjectionPointsAutowiringInspection")
	private Map<String, CordaCustomNodeServiceImpl> services;

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
	private NodeRpcConnection rpcConnection;

	@ModelAttribute("formBean")
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}

	@PostMapping
	public String processSubmit(@Valid ChequeFormBean formBean, BindingResult result,
			@ModelAttribute("ajaxRequest") boolean ajaxRequest, Model model) {

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

		System.out.println("inside processSubmit");

		String returnPage = null;

		if (!model.containsAttribute("formBean")) {
			model.addAttribute("formBean", new ChequeFormBean());
		}
		System.out.println("account id: " + formBean.getAccountNumber());

		if (result.hasErrors()) {
			System.out.println("pageName has error binding            ");
			return null;
		}

		String message = "Form submitted successfully.  Bound " + formBean;

		System.out.println("account number " + formBean.getAccountNumber());

		System.out.println("inside process submit");

		model.addAttribute("formBean", formBean);

		CordaCustomNodeServiceImpl PartyA = services.get(propBankId + "NodeService");

		PartyA.registerChequeBook(formBean, formBean.getFromBankId());

		returnPage = "RegConfirmation";

		return returnPage;
	}
}