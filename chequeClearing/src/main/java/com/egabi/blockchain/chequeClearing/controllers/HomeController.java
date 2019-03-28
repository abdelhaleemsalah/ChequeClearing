package com.egabi.blockchain.chequeClearing.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarInputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import static java.lang.Math.toIntExact;

import com.github.manosbatsis.corbeans.spring.boot.corda.CordaNodeService;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import com.egabi.blockchain.chequeClearing.entities.PortalUser;
import com.egabi.blockchain.chequeClearing.entities.UserRole;
import com.egabi.blockchain.chequeClearing.repositories.UserRepository;
import com.egabi.blockchain.chequeClearing.services.ChequeBookSavingService;
import com.egabi.blockchain.chequeClearing.services.ChequeDetailsSavingService;
import com.egabi.blockchain.chequeClearing.services.CordaCustomNodeServiceImpl;
import com.egabi.blockchain.chequeClearing.services.StorageService;
import com.egabi.blockchain.chequeClearing.services.UserProcessingService;
import com.egabi.blockchain.chequeClearing.utils.HttpDownloadUtility;
import com.github.manosbatsis.corbeans.spring.boot.corda.util.NodeRpcConnection;
import kotlin.Suppress;

@Controller("homeController")
@ControllerAdvice
@SessionAttributes("formBean")
public class HomeController {

	private static final int BUFFER_SIZE = 4096;
	private String defaultNodeName;
	@Autowired
	@Suppress(names = "SpringJavaInjectionPointsAutowiringInspection")
	private Map<String, CordaCustomNodeServiceImpl> services;

	@Autowired
	@Qualifier("HSBCRpcConnection")
	private NodeRpcConnection hsbcRpcConnection;

	@Autowired
	@Qualifier("HSBCNodeService")
	private CordaNodeService hsbcNodeService;

	@Autowired
	private UserProcessingService userProcessingService;

	@Autowired
	StorageService storageService;

	@Autowired
	private ChequeBookSavingService chequeBookSavingService;

	@Autowired
	private ChequeDetailsSavingService ChequeDetailsSavingService;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/{username}/RegConfirmation", method = RequestMethod.POST)
	public String displayConfirmation(@PathVariable("username") String username,
			@ModelAttribute ChequeFormBean formBean, Model model) {
		model.addAttribute("formBean", formBean);
		return "RegConfirmation";
	}

	@RequestMapping(value = "/{username}/bankuserManagement", method = RequestMethod.GET)
	public String displayUserManagement(@PathVariable("username") String username, Model model) {
		if (!model.containsAttribute("userform")) {
			model.addAttribute("userform", new UserFormBean());
			model.addAttribute("username", username);
		}
		System.out.print("hellllo");
		return "bankuserManagement";
	}

	@RequestMapping(value = "/{username}/userCreationSummary", method = RequestMethod.POST)
	public String displayUserCreationSummary(@PathVariable("username") String username,
			@Valid @ModelAttribute(value = "userform") UserFormBean userform, Model model) {
		System.out.print("userCreationSummary username: " + userform.getUsername() + " " + userform.getPassword() + " "
				+ userform.getUserRole() + " " + userform.getUserNationalId());
		PortalUser newCreatedUser = new PortalUser();
		newCreatedUser.setEnabled(true);
		newCreatedUser.setUsername(userform.getUsername());
		newCreatedUser.setPassword(bCryptPasswordEncoder.encode(userform.getPassword()));
		newCreatedUser.setNationalID(userform.getUserNationalId());
		newCreatedUser.setUserId(userform.getUserId());

		// UserRole userRole=new UserRole(userform.getUserRole());

		UserRole userRole = new UserRole();
		if (userform.getUserRole().equals("ENDUSER")) {
			userRole.setRole("ROLE_USER");
			userRole.setUserRoleId(2);
		} else if (userform.getUserRole().equals("BANKUSER")) {
			userRole.setRole("ROLE_ADMIN");
			userRole.setUserRoleId(1);
		}
		newCreatedUser.setUserRole(userRole);
		userProcessingService.saveUserDetails(newCreatedUser);
		return "userCreationSummary";
	}

	@PostConstruct
	public void postConstruct() {
		if (services.keySet().size() == 1) {
			defaultNodeName = services.keySet().iterator().next();
		} else {

		}

	}

	@RequestMapping(value = "/{username}/dashboard", method = RequestMethod.GET)
	public String displaydashboard(@PathVariable("username") String username, Model model) throws IOException {
		if (!model.containsAttribute("formBean")) {

			model.addAttribute("formBean", new ChequeFormBean());
			model.addAttribute("user", username);
		}
		model.addAttribute("files",
				storageService.loadAll(username)
						.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
								path.getFileName().toString(), username).build().toString())
						.collect(Collectors.toList()));
		return "dashboard";
	}

	@RequestMapping(value = "/{username}/RegSummary", method = RequestMethod.GET)
	public String displaySummaryConfirmation(@PathVariable("username") String username,
			@ModelAttribute ChequeFormBean formBean, Model model) {

		CordaCustomNodeServiceImpl partyA = services.get(formBean.getFromBankId() + "NodeService");
		partyA.registerChequeBook(formBean, formBean.getFromBankId());

		return "RegSummary";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayLogin(Model model) {
		model.addAttribute("formBean", new ChequeFormBean());
		return "Registeration";
	}

	@RequestMapping(value = "/{username}/Registeration", method = RequestMethod.GET)
	public String displayHelloLogin(@PathVariable("username") String username, Model model) throws IOException {
		if (!model.containsAttribute("formBean")) {
			model.addAttribute("formBean", new ChequeFormBean());
			model.addAttribute("user", username);

		}
		model.addAttribute("username", username);
		model.addAttribute("files",
				storageService.loadAll(username)
						.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
								path.getFileName().toString(), username).build().toString())
						.collect(Collectors.toList()));
		return "redirect:/flow/registeration";
	}

	@RequestMapping(value = "/{username}/search", method = RequestMethod.GET)
	public ModelAndView displaySearch(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", username);
		return new ModelAndView("search", "formBean", new ChequeFormBean());
	}

	@RequestMapping(value = "/{username}/ChequeDetailsSearch", method = RequestMethod.GET)
	public ModelAndView displayChequeDeatilsSearch(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", username);
		return new ModelAndView("ChequeDetailsSearch", "formBean", new ChequeFormBean());
	}

	@RequestMapping(value = "/{username}/submittedChequesSearch", method = RequestMethod.GET)
	public ModelAndView displaySubmittedChequesSearch(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", username);
		return new ModelAndView("submittedChequesSearch", "formBean", new ChequeFormBean());
	}
	
	@RequestMapping(value = "/{username}/chequeSearchReport", method = RequestMethod.GET)
	public ModelAndView displayChequeReport(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", username);
		return new ModelAndView("chequeSearchReport", "formBean", new ChequeFormBean());
	}

	@RequestMapping(value = "/{username}/chequeSearchReportResult", method = RequestMethod.POST)
	public ModelAndView displayChequeSearchReport(@PathVariable("username") String username,
			@RequestParam("chequeStatus") String chequeStatus, @RequestParam("chequeSerialNo") Integer chequeSerialNo,
			ModelMap model, Model mv) throws NoSuchFieldException, SecurityException {
		ArrayList<ChequeDetail> cheques = new ArrayList<>();
		PortalUser portalUser = userRepository.findPortalUserByUsername(username);
		System.out.println("submittingSummary Page: " + portalUser.getUserId());

		if (chequeSerialNo != null && chequeSerialNo != 0) {
			System.out.println("Cheque SR no: " + chequeSerialNo);
			System.out.println("Cheque staus: " + chequeStatus);

			if (!chequeStatus.equals("SELECT")) {
				// status is not null
				cheques = ChequeDetailsSavingService.findOneWithSRnoAndStatusAndUserId(chequeSerialNo, chequeStatus,
						portalUser.getUserId());
			} else {

				// status is null
				cheques = ChequeDetailsSavingService.findOneWithSRnoAndUserId(chequeSerialNo, portalUser.getUserId());
			}
			for (int i = 0; i < cheques.size(); i++) {
				System.out.println("cheque no:" + i + " username: " + cheques.get(i).getPayToUsername());
			}
		} else {
			cheques = ChequeDetailsSavingService.findOneWithStatusAndUserId(chequeStatus, portalUser.getUserId());
		}
		mv.addAttribute("retrievedCheques", cheques);
		mv.addAttribute("user", username);
		return new ModelAndView("chequeSearchReportResult", "formBean", new ChequeFormBean());
	}
	
	@RequestMapping(value = "/{username}/submittedChequesSearchResult", method = RequestMethod.POST)
	public ModelAndView displaySubmittedChequesSearchResult(@PathVariable("username") String username,
			@RequestParam("chequeDueDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date chequeDueDate,
			ModelMap model, Model mv) throws NoSuchFieldException, SecurityException {
		ArrayList<ChequeDetail> cheques = new ArrayList<>();
		PortalUser portalUser = userRepository.findPortalUserByUsername(username);
		System.out.println("submittedChequesSearchResult Page: " + portalUser.getUserId());

		/*if (chequeSerialNo != null && chequeSerialNo != 0) {
			System.out.println("Cheque SR no: " + chequeSerialNo);
			System.out.println("Cheque staus: " + chequeStatus);

			if (!chequeStatus.equals("SELECT")) {
				// status is not null
				cheques = ChequeDetailsSavingService.findOneWithSRnoAndStatusAndUserId(chequeSerialNo, chequeStatus,
						portalUser.getUserId());
			} else {

				// status is null
				cheques = ChequeDetailsSavingService.findOneWithSRnoAndUserId(chequeSerialNo, portalUser.getUserId());
			}
			for (int i = 0; i < cheques.size(); i++) {
				System.out.println("cheque no:" + i + " username: " + cheques.get(i).getPayToUsername());
			}
		} else {
			cheques = ChequeDetailsSavingService.findOneWithStatusAndUserId(chequeStatus, portalUser.getUserId());
		}
		mv.addAttribute("retrievedCheques", cheques);
		mv.addAttribute("user", username);*/
		return new ModelAndView("submittedChequesSearchResult", "formBean", new ChequeFormBean());
	}


	@RequestMapping(value = "/{username}/chequeSearchReportResultDetails", method = RequestMethod.GET)
	public ModelAndView displayChequeSearchReportResultDetails(@PathVariable("username") String username,
			@RequestParam("chequeSerialNo") long chequeSerialNo, Model model)
			throws NoSuchFieldException, SecurityException, IOException {
		System.out.println("Cheque SR no: " + chequeSerialNo);
		System.out.println("display ChequeSearchReportResultDetails page");

		boolean isVisiable = false;
		ChequeDetail cheque = ChequeDetailsSavingService.findOneChequeWithSRno(chequeSerialNo);
		System.out.println("Cheque username: " + cheque.getPayToUsername());

		ChequeFormBean chequeBean = new ChequeFormBean();

		chequeBean.setChequeSerialNo(toIntExact(cheque.getChequeSrNo()));
		chequeBean.setChequeAmount(cheque.getChequeAmount());
		chequeBean.setChequeDueDate(new Date(cheque.getChequeDueDate().getTime()));
		chequeBean.setPaytoUsername(cheque.getPayToUsername());
		chequeBean.setAccountNumber(cheque.getAccountNo().toString());
		chequeBean.setChequeCurrency(cheque.getChequeCurrency());
		chequeBean.setToBankId(cheque.getBankCode());
		chequeBean.setChequeStatus(cheque.getStatus());
		chequeBean.setCustomerName(cheque.getFromUsername());
		chequeBean.setPaytoAccountNumber(cheque.getPayToAccountNo());
		chequeBean.setChequeImageName(cheque.getChequeImageName());
		chequeBean.setChequeSerialNoFrom(0);
		chequeBean.setChequeSerialNoTo(0);
		chequeBean.setCustomerId(1);
		if (cheque.getIsCrossed().equals("Y"))
			chequeBean.setCrossed(true);
		else
			chequeBean.setCrossed(false);

		if (cheque.getStatus().equals("PENDING REVIEW") || cheque.getStatus().equals("REVIEW REJECTED"))
			isVisiable = true;

		System.out.println("Modify Visiable: " + isVisiable);
		model.addAttribute("modifyVisiablity", isVisiable);
		model.addAttribute("user", username);

		// storageService.loadAll(merchant).map(
		// path ->
		// MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
		// "serveFile", path.getFileName().toString() ,
		// merchant).build().toString())
		// .collect(Collectors.toList())

		model.addAttribute("files",
				storageService.loadFile(username, cheque.getChequeImageName())
						.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
								path.getFileName().toString(), username).build().toString())
						.collect(Collectors.toList()));

		// model.addAttribute("file",
		// storageService.loadFile(username,cheque.getChequeImageName()).map(
		// path ->
		// MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
		// "serveFile", path.getFileName().toString() ,
		// username).build().toString())
		// .collect(Collectors.toList());
		return new ModelAndView("chequeSearchReportResultDetails", "formBean", chequeBean);
	}

	@RequestMapping(value = "/{username}/chequeDetailsEdit", method = RequestMethod.POST)
	public ModelAndView displayChequeDetailsModification(@PathVariable("username") String username,
			@Valid @ModelAttribute("formBean") ChequeFormBean formBean, Model model)
			throws NoSuchFieldException, SecurityException {
		System.out.println("chequeDetailsEdit chequeSerialNo: " + formBean.getChequeSerialNo());
		model.addAttribute("user", username);
		return new ModelAndView("chequeDetailsEdit", "formBean", formBean);
	}

	@RequestMapping(value = "/{username}/chequeDetailsEditSummary", method = RequestMethod.POST)
	public ModelAndView displayChequeDetailsModificationSummary(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes, @PathVariable("username") String username,
			@Valid @ModelAttribute("formBean") ChequeFormBean formBean, Model model) throws IOException {

		String crossedCheque = "Y";
		if (formBean.isCrossed() == false)
			crossedCheque = "N";

		ArrayList<ChequeDetail> cheques = new ArrayList<>();
		cheques = ChequeDetailsSavingService.findOneWithSRno(formBean.getChequeSerialNo());
		if (!file.getOriginalFilename().trim().isEmpty()
				&& !cheques.get(0).getChequeImageName().equalsIgnoreCase(file.getOriginalFilename())) {
			storageService.store(file, username);
			storageService.deleteFile(username, cheques.get(0).getChequeImageName());
			formBean.setChequeImageName(file.getOriginalFilename());

		}

		String chequeStatus = "PENDING REVIEW";
		ChequeDetailsSavingService.updateChequeById(formBean.getChequeDueDate(), formBean.getChequeSerialNo(),
				formBean.getChequeAmount(), crossedCheque, formBean.getPaytoUsername(), chequeStatus,
				formBean.getChequeImageName());
		model.addAttribute("user", username);
		return new ModelAndView("chequeDetailsEditSummary");
	}

	@RequestMapping(value = "/{username}/ChequeDetailsSearchResult", method = RequestMethod.POST)
	public ModelAndView displayChequeDetailsSearchResult(@PathVariable("username") String username,
			@RequestParam("chequeDueDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date chequeDueDate,
			@RequestParam("chequeStatus") String chequeStatus, @RequestParam("chequeSerialNo") Integer chequeSerialNo,
			ModelMap model, Model mv) throws NoSuchFieldException, SecurityException {
		String returnPage = null;
		ChequeFormBean singleChequeFormBean = new ChequeFormBean();
		ArrayList<ChequeDetail> cheques = new ArrayList<>();
		if (chequeSerialNo == null || chequeSerialNo == 0) {
			System.out.println("Cheque SR no: " + chequeSerialNo);
			System.out.println("Cheque due date: " + chequeDueDate);
			System.out.println("Cheque status: " + chequeStatus);

			if (!chequeStatus.equals("SELECT")) {
				// status is not null
				if (chequeDueDate != null)
					cheques = ChequeDetailsSavingService.findOneWithStatusAndDuedate(chequeStatus, chequeDueDate);
				else
					cheques = ChequeDetailsSavingService.findOneWithStatus(chequeStatus);
			} else {
				// status is null
				if (chequeDueDate != null)
					cheques = ChequeDetailsSavingService.findOneWithDuedate(chequeDueDate);
			}
		} else {
			System.out.println("Cheque SR no: " + chequeSerialNo);
			System.out.println("Cheque due date: " + chequeDueDate);
			System.out.println("Cheque staus: " + chequeStatus);

			if (!chequeStatus.equals("SELECT")) {
				// status is not null
				if (chequeDueDate != null) {
					cheques = ChequeDetailsSavingService.findOneWithSRnoAndStatusAndDuedate(chequeSerialNo,
							chequeStatus, chequeDueDate);
				} else
					cheques = ChequeDetailsSavingService.findOneWithSRnoAndStatus(chequeSerialNo, chequeStatus);
			} else {
				// status is null
				if (chequeDueDate != null)
					cheques = ChequeDetailsSavingService.findOneWithSRnoAndDuedate(chequeSerialNo, chequeDueDate);

				// if all params are null except sr no
				else
					cheques = ChequeDetailsSavingService.findOneWithSRno(chequeSerialNo);
			}
		}
		for (int i = 0; i < cheques.size(); i++) {
			System.out.println("cheque no:" + i + " username: " + cheques.get(i).getPayToUsername());
		}
		mv.addAttribute("retrievedCheques", cheques);

		mv.addAttribute("user", username);

		return new ModelAndView("ChequeDetailsSearchResult", "formBean", singleChequeFormBean);
	}

	@RequestMapping(value = "/{username}/chequesApproval", method = RequestMethod.GET)
	public String displayApprovalPage(@PathVariable("username") String username,
			@RequestParam("chequeSerialNo") long chequeSerialNo, Model model)
			throws NoSuchFieldException, SecurityException, ParseException, IOException {
		boolean submitVisiable = false;
		System.out.println("Cheque SR no: " + chequeSerialNo);
		System.out.println("display approval page");

		ChequeDetail cheque = ChequeDetailsSavingService.findOneChequeWithSRno(chequeSerialNo);
		System.out.println("Cheque username: " + cheque.getPayToUsername());

		ChequeFormBean chequeBean = new ChequeFormBean();

		chequeBean.setChequeSerialNo(toIntExact(cheque.getChequeSrNo()));
		chequeBean.setChequeAmount(cheque.getChequeAmount());
		chequeBean.setChequeDueDate(new Date(cheque.getChequeDueDate().getTime()));
		chequeBean.setPaytoUsername(cheque.getPayToUsername());
		chequeBean.setAccountNumber(cheque.getAccountNo().toString());
		chequeBean.setChequeCurrency(cheque.getChequeCurrency());
		chequeBean.setToBankId(cheque.getBankCode());
		chequeBean.setChequeStatus(cheque.getStatus());
		chequeBean.setCustomerName(cheque.getFromUsername());
		chequeBean.setPaytoAccountNumber(cheque.getPayToAccountNo());
		chequeBean.setChequeImageName(cheque.getChequeImageName());
		if (cheque.getIsCrossed().equals("Y"))
			chequeBean.setCrossed(true);
		else
			chequeBean.setCrossed(false);

		if (cheque.getChequeDueDate().compareTo(new Date()) <= 0) {
			submitVisiable = true;
		}

		model.addAttribute("user", username);
		model.addAttribute("username", username);
		model.addAttribute("submitVisiablity", submitVisiable);
		model.addAttribute("formBean", chequeBean);
		model.addAttribute("chequeSerialNo", chequeSerialNo);
		model.addAttribute("files",
				storageService.loadFile(cheque.getUserID().getUsername(), cheque.getChequeImageName())
						.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
								path.getFileName().toString(), cheque.getUserID().getUsername()).build().toString())
						.collect(Collectors.toList()));

		return "redirect:/flow/chequeReview";
		// return new ModelAndView("flow/chequeReview", "formBean",chequeBean);
	}

	@RequestMapping(value = "/{username}/approvalSummary", method = RequestMethod.POST)
	public ModelAndView displayApprovalSummary(@PathVariable("username") String username,
			@RequestParam("chequeSerialNo") long chequeSerialNo, Model model)
			throws NoSuchFieldException, SecurityException 
	{
		model.addAttribute("user", username);
		return new ModelAndView("approvalSummary", "formBean", new ChequeFormBean());
	}

	@RequestMapping(value = "/{username}/rejectionSummary", method = RequestMethod.POST)
	public ModelAndView displayRejectionSummary(@PathVariable("username") String username,
			@RequestParam("chequeSerialNo") long chequeSerialNo, Model model)
			throws NoSuchFieldException, SecurityException {
		model.addAttribute("user", username);
		return new ModelAndView("rejectionSummary", "formBean", new ChequeFormBean());
	}

	@RequestMapping(value = "/{username}/SearchResult", method = RequestMethod.POST)
	public ModelAndView displaySearchResult(@PathVariable("username") String username,
			@RequestParam("chequeSerialNo") Integer serialno, @RequestParam("fromBankId") String bankid,
			@RequestParam("accountNumber") long accNo, Model m, ModelMap model)
			throws NoSuchFieldException, SecurityException {

		String returnPage = null;
		ChequeFormBean singleChequeFormBean = new ChequeFormBean();
		System.out.println("Cheque SR no: " + serialno);
		System.out.println("Cheque bank id: " + bankid);
		System.out.println("Cheque account no: " + accNo);

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
		// if (bankid.equalsIgnoreCase(propBankId))
		// {
		// return null;
		// }
		model.addAttribute("user", username);

		CordaCustomNodeServiceImpl PartyA = services.get(bankid + "NodeService");

		// PartyA.getNodeRpcConnection().getProxy().u
		singleChequeFormBean = PartyA.retrieveChequeBook(bankid, accNo, serialno);

		return new ModelAndView("SearchResult", "formBean", singleChequeFormBean);
	}

	@ModelAttribute("formBean")
	public ChequeFormBean createFormBean() {
		return new ChequeFormBean();
	}

	@RequestMapping(value = "/{username}/submittingSummary", method = RequestMethod.POST)
	public String chequeSubmittingSummary(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes, @PathVariable("username") String username,
			@Valid @ModelAttribute("formBean") ChequeFormBean formBean, Model model) {

		System.out.println("submitting summary hello");
		System.out.println("submitting summary: " + formBean.getPaytoUsername());
		System.out.println("submitting summary: " + formBean.getPaytoAccountNumber());

		ChequeDetail submittedCheque = new ChequeDetail();

		PortalUser portalUser = userRepository.findPortalUserByUsername(username);
		System.out.println("submittingSummary Page: " + portalUser.getUserId());

		if (formBean.isCrossed() == false)
			submittedCheque.setIsCrossed("N");
		else
			submittedCheque.setIsCrossed("Y");

		submittedCheque.setAccountNo(formBean.getAccountNumber());
		submittedCheque.setBankCode(formBean.getFromBankId());
		submittedCheque.setChequeAmount(formBean.getChequeAmount());
		submittedCheque.setChequeBookId(formBean.getChequeBookSerialNo());
		submittedCheque.setChequeCurrency(formBean.getChequeCurrency());
		submittedCheque.setChequeDueDate(new Timestamp(formBean.getChequeDueDate().getTime()));
		submittedCheque.setChequeSrNo(formBean.getChequeSerialNo());
		submittedCheque.setPayToUsername(formBean.getPaytoUsername());
		submittedCheque.setPayToAccountNo(formBean.getPaytoAccountNumber());
		submittedCheque.setFromUsername(formBean.getCustomerName());
		submittedCheque.setStatus("PENDING REVIEW");
		submittedCheque.setUserID(portalUser);
		submittedCheque.setChequeImageName(file.getOriginalFilename());
		submittedCheque.setChequeInitialDate(new Timestamp(new Date().getTime()));
		submittedCheque.setChequeModificationDate(new Timestamp(new Date().getTime()));
		
		ChequeDetailsSavingService.saveCheque(submittedCheque);

		storageService.store(file, username);

		return "submittingSummary";
	}

	public void approveCheque(ChequeFormBean formBean) {
		System.out.println("Cheque Approved SR no: " + formBean.getChequeSerialNo());
		ChequeDetailsSavingService.setChequeInfoById("REVIEW APPROVED", new Timestamp(new Date().getTime()), formBean.getChequeSerialNo());
	}

	public void rejectCheque(ChequeFormBean formBean) {
		System.out.println("Cheque Rejected SR no: " + formBean.getChequeSerialNo());
		ChequeDetailsSavingService.setChequeInfoById("REVIEW REJECTED", new Timestamp(new Date().getTime()), formBean.getChequeSerialNo());
	}



	public void writeToZipFile(String path, ZipOutputStream zipStream) throws FileNotFoundException, IOException {
		System.out.println("Writing file : '" + path + "' to zip file");
		File aFile = new File(path);
		//aFile.createNewFile();
		
		FileInputStream fis = new FileInputStream(aFile);
		ZipEntry zipEntry = new ZipEntry(path);
		zipStream.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipStream.write(bytes, 0, length);
		}
		zipStream.closeEntry();
		zipStream.close();
		fis.close();
		
	}
	
	
	
	
    @RequestMapping(value = "/zip",produces="application/zip")
    public byte[] zipFiles(HttpServletResponse response) throws IOException{
        //setting headers
//        response.setContentType("application/zip");
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

        //creating byteArray stream, make it bufforable and passing this buffor to ZipOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        //simple file list, just for tests
        ArrayList<File> files = new ArrayList<>(2);
        files.add(new File("README.md"));

        //packing files
        for (File file : files) {
            //new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }

        if (zipOutputStream != null) {
            zipOutputStream.finish();
            zipOutputStream.flush();
            IOUtils.closeQuietly(zipOutputStream);
        }
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

   // http://localhost:8080/chequeClearing/files/enduser/postman.png
//    public String getFileName(String path)
//    {
//    	int slashindex=path.lastIndexOf("/");
//    	String filename=path.substring(slashindex,path.length());
//    	return filename;
//    }
    public String getDirectoryName(String path)
    {
    	int slashindex=path.lastIndexOf("/");
    	String pathWithoutFileName=path.substring(1,slashindex);
    	int lastslashindex=pathWithoutFileName.lastIndexOf("/");
    	String directoryName=pathWithoutFileName.substring(lastslashindex+1,pathWithoutFileName.length());
    	return directoryName;
    }
	public void submitCheque(ChequeFormBean formBean, String filePaths) throws IOException {

//		InputStream input = null;
		Properties prop = new Properties();
		String directoryName=getDirectoryName(filePaths);
//
//		Path filePath = null;
		
		
	    Resource file = storageService.loadAsResource(formBean.getChequeImageName(), directoryName);
//		URL files = new URL(file.getURL());
		//FileURLConnection conn = (HttpURLConnection) file.getURL().openConnection();
//
		
//		URL url = null;
//		URLConnection con = null;
//		int i;
//	
//			url = new URL(filePaths);
//			con = url.openConnection();
//			File files = new File(
//					"E:/Download/test.png");
//			 ReadableByteChannel rbc = Channels.newChannel(url.openStream());
//	            FileOutputStream fos = new FileOutputStream(files);
//	            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//	            fos.close();
//	            rbc.close();
//
//	
//	
////	try {
////HttpDownloadUtility.downloadFile(filePaths, saveDir);
////} catch (IOException ex) {
////ex.printStackTrace();
////}

	
//String fileURL = "http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar";
		String saveDir = "E:/Download/test.jar";
		File f=new File("E:/Download/test.jar");
		f.createNewFile();
FileOutputStream foss = new FileOutputStream(f.getPath());
FileInputStream fileStream = new FileInputStream(f.getPath());
		ZipOutputStream zipOS = new ZipOutputStream(foss);
		writeToZipFile(file.getURL().getPath(), zipOS);
		// Read more:
		// http://www.java67.com/2016/12/how-to-create-zip-file-in-java-zipentry-example.html#ixzz5jBC0rBz4
		InputStream inputStream = null;
		Properties props = new Properties();
		String propBankId = "";
		try {
			inputStream = resourceLoader.getResource("classpath:bankconfig.properties").getInputStream();
			prop.load(inputStream);
			System.out.println("prop get bank id: " + prop.getProperty("mybank"));
			propBankId = prop.getProperty("mybank");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CordaCustomNodeServiceImpl partyA = services.get(propBankId + "NodeService");
		CordaCustomNodeServiceImpl partyCBE = services.get("CBE" + "NodeService");
		// String toBank=formBean.getToBankId();
		// formBean.setToBankId("CBE");
		
		
        BufferedInputStream bis = new BufferedInputStream(fileStream);
        ZipInputStream zis = new ZipInputStream(bis);
		
        JarInputStream jarstream=new JarInputStream(fileStream);
		
		partyA.submitCheque(formBean, jarstream, "CBE");
		partyCBE.submitCheque(formBean, jarstream, formBean.getToBankId());

		// submission execution
	}

	public void registerChequeBook(ChequeFormBean formBean) {
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
		// formBean.setBankId(propBankId);
		CordaCustomNodeServiceImpl partyA = services.get(propBankId + "NodeService");
		partyA.registerChequeBook(formBean, propBankId);
	}

	public ChequeFormBean initiateFormBean() {
		ChequeFormBean formBeam = new ChequeFormBean();
		return formBeam;
	}

	public ChequeFormBean getChequeDetails(long chequeSerialNo) {
		ChequeDetail cheque = ChequeDetailsSavingService.findOneChequeWithSRno(chequeSerialNo);
		ChequeFormBean chequeBean = new ChequeFormBean();

		System.out.println("inside getChequeDetails method");

		chequeBean.setChequeSerialNo(toIntExact(cheque.getChequeSrNo()));
		chequeBean.setChequeAmount(cheque.getChequeAmount());
		chequeBean.setChequeDueDate(new Date(cheque.getChequeDueDate().getTime()));
		chequeBean.setPaytoUsername(cheque.getPayToUsername());
		chequeBean.setAccountNumber(cheque.getAccountNo().toString());
		chequeBean.setChequeCurrency(cheque.getChequeCurrency());
		chequeBean.setToBankId(cheque.getBankCode());
		chequeBean.setChequeStatus(cheque.getStatus());
		chequeBean.setCustomerName(cheque.getFromUsername());
		chequeBean.setPaytoAccountNumber(cheque.getPayToAccountNo());
		chequeBean.setChequeImageName(cheque.getChequeImageName());

		chequeBean.setChequeSerialNoFrom(0);
		chequeBean.setChequeSerialNoTo(0);
		chequeBean.setCustomerId(0);

		if (cheque.getIsCrossed().equals("Y"))
			chequeBean.setCrossed(true);
		else
			chequeBean.setCrossed(false);

		return chequeBean;
	}
}
