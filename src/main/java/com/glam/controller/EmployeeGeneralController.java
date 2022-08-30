package com.glam.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.glam.beans.Branch;
import com.glam.beans.EmployeeDomain;
import com.glam.beans.EmployeeGeneral;
import com.glam.beans.Store;
import com.glam.repository.EmployeeGeneralRepository;
import com.glam.services.BranchService;
import com.glam.services.GlamService;
import com.glam.services.StoreService;
import com.glam.utils.Utilities;

@Controller

public class EmployeeGeneralController {

	@Autowired
	private GlamService glamService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private BranchService branchService;
	

	@GetMapping("/employeeList/{storeID}")
	public String viewAllEmployees(Model model, @PathVariable("storeID") int storeID) {
		
		List<EmployeeGeneral> employeeListByID = glamService.getAllEmployeesByStoreID(storeID);
		
		int count = employeeListByID.size();
		List<EmployeeGeneral> employeeList = new ArrayList<>();
		for(int i=0; i<count; i++) {
			
			int employeeID = employeeListByID.get(i).getEmployeeId();
			EmployeeGeneral employeeGeneral = glamService.getEmployeeById(employeeID);
			Store store = storeService.getStoreById(employeeGeneral.getStoreID());
			Branch branch = branchService.getBranchById(employeeGeneral.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(employeeGeneral.getDomainID());
			employeeGeneral.setStoreName(store.getStoreName());
			employeeGeneral.setBranchName(branch.getBranchName());
			employeeGeneral.setDomainName(domain.getDomainName());
			employeeList.add(employeeGeneral);
		} 
		
		model.addAttribute("EmployeeList", glamService.getAllEmployeesByStoreID(storeID));
		return "employeeList";
	}

	@GetMapping("/employeelist/{storeID}/{branchID}")
	public String EmployeesByStoreandBranch(Model model, @PathVariable("storeID") int storeID,
			@PathVariable("branchID") int branchID) {

		List<EmployeeGeneral> employeeListByID = glamService.getAllEmployeesByStoreIDandBranchID(storeID, branchID);

		int count = employeeListByID.size();
		List<EmployeeGeneral> employeeList = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int employeeID = employeeListByID.get(i).getEmployeeId();
			EmployeeGeneral employeeGeneral = glamService.getEmployeeById(employeeID);
			Store store = storeService.getStoreById(employeeGeneral.getStoreID());
			Branch branch = branchService.getBranchById(employeeGeneral.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(employeeGeneral.getDomainID());
			employeeGeneral.setDomainName(domain.getDomainName());
			employeeGeneral.setStoreName(store.getStoreName());
			employeeGeneral.setBranchName(branch.getBranchName());

			employeeList.add(employeeGeneral);

		}

		model.addAttribute("EmployeeList", glamService.getAllEmployeesByStoreIDandBranchID(storeID, branchID));
		return "employeeList";
	}
	
	@GetMapping("/employeelist/{storeID}/{branchID}/{domainID}")
	public String EmployeesByStoreBranchandDomain(Model model, @PathVariable("storeID") int storeID,
			@PathVariable("branchID") int branchID, @PathVariable("domainID") int domainID) {

		List<EmployeeGeneral> employeeListByID = glamService.getAllEmployeesByStoreIDBranchIDandDomainID(storeID, branchID, domainID);

		int count = employeeListByID.size();
		List<EmployeeGeneral> employeeList = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int employeeID = employeeListByID.get(i).getEmployeeId();
			EmployeeGeneral employeeGeneral = glamService.getEmployeeById(employeeID);
			Store store = storeService.getStoreById(employeeGeneral.getStoreID());
			Branch branch = branchService.getBranchById(employeeGeneral.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(employeeGeneral.getDomainID());
			employeeGeneral.setDomainName(domain.getDomainName());
			employeeGeneral.setStoreName(store.getStoreName());
			employeeGeneral.setBranchName(branch.getBranchName());

			employeeList.add(employeeGeneral);

		}

		model.addAttribute("EmployeeList", glamService.getAllEmployeesByStoreIDBranchIDandDomainID(storeID, branchID, domainID));
		return "employeeList";
	}
	

	@RequestMapping(value = "/EmployeeRegistration", method = RequestMethod.GET)
	public String EmpRegistrationForm(Model model, @ModelAttribute(value = "EmpObj") EmployeeGeneral empobj,
			HttpServletRequest request) {

		String EmpObjExisting1 = glamService.getEmployeeEmailId(empobj.getEmployeeEmailId());

		if (EmpObjExisting1 != null) {

			EmployeeGeneral empobj3 = new EmployeeGeneral();

			model.addAttribute("EmpObj", empobj3);
			model.addAttribute("error",
					"Seems like you are registered with us already  --------->>> PLEASE TRY SIGNIGN IN TO CONTINUE");
			return "homepage";

		} else {
			System.out.println("new registration ");

			String strEncPassword = Utilities.getEncryptSecurePassword(empobj.getEmployeePassword(), "GLAM");
			empobj.setEmployeePassword(strEncPassword);
			EmployeeGeneral empObj = glamService.addEmployee(empobj);

			HttpSession session = request.getSession();
			session.setAttribute("Name", empObj.getEmployeeName());

			int empID = empObj.getEmployeeId();
			model.addAttribute("empID", empID);

			model.addAttribute("EmpObj", empobj);
			
			model.addAttribute("AddProfileMsg","Add Your Profile to get completely Registered with us");


			return "empRegHome";

		}

	}
	
	
	
	
	@RequestMapping("/verificationEmp")
	public String validatemail(Model model, EmployeeGeneral employeeGeneral, final HttpServletRequest request,
	final HttpServletResponse response,HttpSession session ) throws MessagingException{
	final StringBuffer uri = request.getRequestURL();


	model.addAttribute("Emailid",employeeGeneral.getEmployeeEmailId());
	SimpleMailMessage mailMessage = new SimpleMailMessage();

	mailMessage.setTo(employeeGeneral.getEmployeeEmailId());

	mailMessage.setSubject("Complete Registration!");

	mailMessage.setFrom("glamservices5@gmail.com");

	mailMessage.setText("To confirm your account, please click here : "
	+session.getAttribute("url")+"/EmployeeRegistration?employeeEmailId="+employeeGeneral.getEmployeeEmailId()+"&employeePassword="+employeeGeneral.getEmployeePassword()+"&employeeMobile="+employeeGeneral.getEmployeeMobile()+"&employeeName="+employeeGeneral.getEmployeeName());
//	System.out.println("::::::::::::::::: 6"+session.getAttribute("url")+"saveuser?adminEmail="+adminObj.getAdminEmail()+"&adminPassword="+adminObj.getAdminPassword());
	// mailMessage.setText("To confirm your account, please click here : "
	// +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());
	storeService.sendSimpleEmail(employeeGeneral.getEmployeeEmailId(),"To confirm your account, please click here : "
	+"http://localhost:9090/"+"EmployeeRegistration?employeeEmailId="+employeeGeneral.getEmployeeEmailId()+"&employeePassword="+employeeGeneral.getEmployeePassword()+"&employeeMobile="+employeeGeneral.getEmployeeMobile()+"&employeeName="+employeeGeneral.getEmployeeName(),"Email Verification" );
	//emailSenderService.sendEmail(mailMessage);

	System.out.println("http://localhost:9090/"+"EmployeeRegistration?employeeEmailId="+employeeGeneral.getEmployeeEmailId()+"&employeePassword="+employeeGeneral.getEmployeePassword()+"&employeeMobile="+employeeGeneral.getEmployeeMobile()+"&employeeName="+employeeGeneral.getEmployeeName());
	model.addAttribute("Emailid",employeeGeneral.getEmployeeEmailId());

	return "successfullyRegistered";
	
	}
	
	@GetMapping("/editEmpProfile/{empID}")
	public String viewemployeeForm(Model model, @PathVariable("empID") int empID, EmployeeGeneral empobj1) {

		EmployeeGeneral employee = glamService.getEmployeeById(empID);

		model.addAttribute("EmpObj", empobj1);



		model.addAttribute("EmpObj", glamService.getEmployeeById(empID));
		model.addAttribute("empID", glamService.getEmployeeById(empID).getEmployeeId());

		model.addAttribute("storeList", storeService.getAllStore());

		List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(employee.getStoreID()));
		model.addAttribute("branchList", branchList);

		List<EmployeeDomain> domainList = glamService.getAllDomainsBybranchID(Integer.valueOf(employee.getBranchID()));
		model.addAttribute("domainList", domainList);
		return "employeeForm";

	}
	
	@RequestMapping("/dropdown")
	public String dropdown(Model model,final HttpServletRequest request,@ModelAttribute("EmpObj") EmployeeGeneral empobj) {
		
		model.addAttribute("EmpObj", empobj);

		List<Store> storeList = storeService.getAllStore();
		model.addAttribute("storeList", storeList);
		String getQry =request.getParameter("getQry");
		String storeID =request.getParameter("storeID");
		System.out.println(storeID+" "+getQry);
		if(getQry !=null && getQry.equals("editEmpProfile") && storeID !=null && storeID!="" ){
		List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
		model.addAttribute("branchList", branchList);
		System.out.println(storeID+" "+getQry);
		
		return "employeeForm";
}
		List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(empobj.getStoreID()));
		model.addAttribute("branchList", branchList);
		String getQrybranch = request.getParameter("getQrybranch");
		String branchID = request.getParameter("branchID");
		
		if(getQrybranch !=null && getQrybranch.equals("editEmpProfileBranch") && branchID !=null && branchID !="") {
			List<EmployeeDomain> domainList = glamService.getAllDomainsBybranchID(Integer.valueOf(branchID));
			model.addAttribute("domainList", domainList);
			
			return "employeeForm";
		}

		int empID = empobj.getEmployeeId();

		model.addAttribute("EmpObj", empobj);
		model.addAttribute("empID", glamService.getEmployeeById(empID).getEmployeeId());
		
		
		
		return"";
	}
	

	@PostMapping("/uploadEmpProfile")
	public String uploadEmpProfile(@ModelAttribute("EmpObj") EmployeeGeneral empobj,Model model,final HttpServletRequest request, @RequestParam("file") MultipartFile file ) throws IOException {

		model.addAttribute("EmpObj", empobj);
		model.addAttribute("EmployeeList", glamService.getAllEmployees());
		
		Branch egbn=branchService.getBranchById(empobj.getBranchID());
		empobj.setBranchName(egbn.getBranchName());
		
		Store egsn=storeService.getStoreById(empobj.getStoreID());
		empobj.setStoreName(egsn.getStoreName());
		
		EmployeeDomain egdn = glamService.getDomainById(empobj.getDomainID());
		empobj.setDomainName(egdn.getDomainName());
		
		if(file.getOriginalFilename()=="") {
			empobj.setEmployeeImage(empobj.getEmployeeImage());

					 }else {

						 empobj.setEmployeeImage(Base64.getEncoder().encodeToString(file.getBytes()));

					 }
		glamService.addEmployee(empobj);
		
		int empID = empobj.getEmployeeId();
        model.addAttribute("EmpObj", empobj);
		model.addAttribute("empID", glamService.getEmployeeById(empID).getEmployeeId());
		
		
		return "empRegHome";
	}
	
	@GetMapping("/updatedEmpProfile/{empID}")
	public String viewUodateEmpProfile(Model model, @PathVariable("empID") int empID, EmployeeGeneral empobj) {

		model.addAttribute("EmpObj", empobj);
		


		model.addAttribute("EmpObj", glamService.getEmployeeById(empID));
		model.addAttribute("empID", glamService.getEmployeeById(empID).getEmployeeId());

		return "empDashboard";
	}
	
	@GetMapping("/updateEmpProfile/{empID}")
	public String viewUodateEmpProfileForm(Model model, @PathVariable("empID") int empID, EmployeeGeneral empobj) {

		model.addAttribute("EmpObj", empobj);

		model.addAttribute("EmpObj", glamService.getEmployeeById(empID));
		model.addAttribute("empID", glamService.getEmployeeById(empID).getEmployeeId());

		return "empRegDashboard";
	}
	
	@GetMapping("/updatedCompleteEmpProfile/{empID}")
	public String viewUpdatedCompleteEmpProfile(Model model, @PathVariable("empID") int empID, EmployeeGeneral empobj) {


		model.addAttribute("EmpObj", empobj);

		model.addAttribute("EmpObj", glamService.getEmployeeById(empID));
		model.addAttribute("empID", glamService.getEmployeeById(empID).getEmployeeId());

		EmployeeGeneral emp1 = glamService.getEmployeeById(empID);
		Store store = storeService.getStoreById(emp1.getStoreID());
		Branch branch = branchService.getBranchById(emp1.getBranchID());
		EmployeeDomain domain = glamService.getDomainById(emp1.getDomainID());
		emp1.setDomainName(domain.getDomainName());
		emp1.setStoreName(store.getStoreName());
		emp1.setBranchName(branch.getBranchName());

		model.addAttribute("storeName", emp1.getStoreName());
		model.addAttribute("branchName", emp1.getBranchName());
		model.addAttribute("domainName", emp1.getDomainName());
		

		return "empDashboard";
	}

	@GetMapping("/sign-inE")
	public String signinginUser(Model model, @ModelAttribute(value = "EmpObj") EmployeeGeneral empobj2,
			HttpServletRequest request) {
		System.out.println(empobj2.getEmployeePassword());

		String strEncPassword = Utilities.getEncryptSecurePassword(empobj2.getEmployeePassword(), "GLAM");

		EmployeeGeneral EmpObjExisting = glamService.getEmployee(empobj2.getEmployeeEmailId(), strEncPassword);

		if (EmpObjExisting != null) {
			HttpSession session = request.getSession();

			int empID = EmpObjExisting.getEmployeeId();
			model.addAttribute("empID", empID);
			session.setAttribute("Name", EmpObjExisting.getEmployeeName());

			model.addAttribute("EmpObj", EmpObjExisting);
			return "empSignInHome";

		} else {
			EmployeeGeneral empobj3 = new EmployeeGeneral();
			model.addAttribute("EmpObj", empobj3);

			model.addAttribute("errormsg3", "In order to sign-in,you need to be registered with us");
			model.addAttribute("errormsg2", "--->>> If you have already registered with us");
			model.addAttribute("errormsg1", "--->>> PLEASE TRY SIGNING IN AGAIN WITH THE CORRECT EMAILiD and PASSWORD");

			return "homepage";

		}
	}
	
	@RequestMapping(value="/forgotPasswordThree", method=RequestMethod.GET)
	public ModelAndView UserforgotPasswordPageThree(EmployeeGeneral empobj) {
	System.out.println("entered into user/controller::::forgot password method");
	ModelAndView mav=new ModelAndView("forgotPasswordThree");
	mav.addObject("EmpObj",empobj);
	return mav;
	}
	@PostMapping(value ="/validateEmailThree")
	public String checkMail(Model model,EmployeeGeneral empobj) {
	System.out.println("entered into user/controller::::check Email existing or not");
	System.out.println("UI given mail Id:"+empobj.getEmployeeEmailId());
	EmployeeGeneral EmpObj= glamService.findbyEmail(empobj.getEmployeeEmailId());
	if(EmpObj!=null) {
	String s1="";
	model.addAttribute("message",s1);
	System.out.println("UI given mail Id:"+EmpObj.getEmployeeEmailId());
	model.addAttribute("EmpObj",empobj);
	return "resetPasswordThree";
	}
	else {
	System.out.println("Invalid Mail");
	String s1="Email-Id Not Exists";
	model.addAttribute("message",s1);
	model.addAttribute("EmpObj", new EmployeeGeneral());
	return "forgotPasswordThree";
	}
	}
	
	@RequestMapping(value = "/updateClientPasswordThree", method = RequestMethod.POST)
	public ModelAndView updateUserPasswordThree(Model model,@ModelAttribute("EmpObj") EmployeeGeneral empobj) {
  EmployeeGeneral employee=glamService.findbyEmail(empobj.getEmployeeEmailId());
	System.out.println("inside updateClientPasswordThree after update id is:::"+empobj.getEmployeeId());
	String strEncPassword = Utilities.getEncryptSecurePassword(empobj.getEmployeePassword(),"GLAM");
	empobj.setEmployeePassword(strEncPassword);
	employee.setEmployeePassword(empobj.getEmployeePassword());
	System.out.println(empobj.getEmployeePassword());
	
	System.out.println("in update method customer:: name "+employee.getEmployeeName());
	glamService.updateEmployee(employee);
	
	System.out.println("password is updated sucessfully");
	ModelAndView mav=new ModelAndView("homepage");
	System.out.println("login page is displayed");
	model.addAttribute("EmpObj", employee);
	return mav;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("Name", null);
		return "redirect:/";
	}

}