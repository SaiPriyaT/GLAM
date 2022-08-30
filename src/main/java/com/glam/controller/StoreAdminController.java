package com.glam.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;

import com.glam.beans.StoreAdmin;
import com.glam.services.StoreService;
import com.glam.utils.Utilities;



@Controller
public class StoreAdminController {


	private static final Logger log = Logger.getLogger(OurCareController.class);
	
	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/saveStoreAdmin", method = RequestMethod.GET)
	public String EmpRegistrationForm(Model model, @ModelAttribute(value = "AdminObj") StoreAdmin adminObj,
			HttpServletRequest request) {
		/*
		 * if (!adminObj.getCaptcha().equals(adminObj.getAdminCaptcha())) {
		 * model.addAttribute("message", "Please enter valid Captcha"); return
		 * "homepage"; }
		 */
		

		String adminobjExisting = storeService.getAdminEmailId(adminObj.getAdminEmail());
//StoreAdmin adminobjExisting = storeService.getAdminEmailId(adminObj.getAdminEmail());

		if (adminobjExisting != null) {
			System.out.println("skip it");

			StoreAdmin storeAdmin = new StoreAdmin();

			model.addAttribute("AdminObj", storeAdmin);
			model.addAttribute("error", "you cannot register more than once,please sign-in to continue");
			return "homepage";

		} else {
			System.out.println("new registration ");

			String strEncPassword = Utilities.getEncryptSecurePassword(adminObj.getAdminPassword(), "GLAM");
			adminObj.setAdminPassword(strEncPassword);
			StoreAdmin obj = storeService.addAdmin(adminObj);

			HttpSession session = request.getSession();
			session.setAttribute("adminObj", obj.getAdminName());
			session.setAttribute("adminID", obj.getAdminID());

			model.addAttribute("AdminObj", adminObj);
			model.addAttribute("adminID", adminObj.getAdminID());
//model.addAttribute("newsid", obj));
			model.addAttribute("News", obj);
//model.addAttribute("example", obj.getNewsDescription());
//model.addAttribute("n", adminObj.);
			return "redirect:/welcome";

		}
	}
	
	@RequestMapping("/verification")
	public String validatemail(Model model, StoreAdmin adminObj, final HttpServletRequest request,
	final HttpServletResponse response,HttpSession session ) throws MessagingException{
	final StringBuffer uri = request.getRequestURL();


	model.addAttribute("Emailid",adminObj.getAdminEmail());
	SimpleMailMessage mailMessage = new SimpleMailMessage();

	mailMessage.setTo(adminObj.getAdminEmail());

	mailMessage.setSubject("Complete Registration!");

	mailMessage.setFrom("glamservices5@gmail.com");

	mailMessage.setText("To confirm your account, please click here : "
	+session.getAttribute("url")+"/saveuser?adminMailId="+adminObj.getAdminEmail()+"&adminPassword="+adminObj.getAdminPassword());
	System.out.println("::::::::::::::::: 6"+session.getAttribute("url")+"saveuser?adminEmail="+adminObj.getAdminEmail()+"&adminPassword="+adminObj.getAdminPassword());
	// mailMessage.setText("To confirm your account, please click here : "
	// +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());
	storeService.sendSimpleEmail(adminObj.getAdminEmail(),"To confirm your account, please click here : "
	+"http://localhost:9090/"+"saveStoreAdmin?adminEmail="+adminObj.getAdminEmail()+"&adminPassword="+adminObj.getAdminPassword(),"Email Verification" );
	//emailSenderService.sendEmail(mailMessage);

	model.addAttribute("Emailid",adminObj.getAdminEmail());

	return "successfullyRegistered";
	
	}
	
	
	
	
	

	@GetMapping("/welcome")
	public String welcome(Model model, @ModelAttribute(value = "AdminObj") StoreAdmin adminObj) {

		model.addAttribute("AdminObj", adminObj);
		return "adminProfile";
	}

	@GetMapping("/sign-inAdmin")
	public String signinginUser(Model model, @ModelAttribute(value = "AdminObj") StoreAdmin adminLog,
			HttpServletRequest request) {

		if (!adminLog.getCaptcha().equals(adminLog.getAdminCaptcha())) {
			model.addAttribute("message", "Please enter valid Captcha");
			return "homepage";
		}

		String strEncPassword = Utilities.getEncryptSecurePassword(adminLog.getAdminPassword(), "GLAM");

		StoreAdmin adminObjExisting = storeService.getAdmin(adminLog.getAdminEmail(), strEncPassword);

		model.addAttribute("AdminObj", adminObjExisting);
		model.addAttribute("hello", adminObjExisting);

		if (adminObjExisting != null) {
			HttpSession session = request.getSession();
			session.setAttribute("adminObj", adminObjExisting.getAdminName());

			model.addAttribute("name", adminObjExisting.getAdminName());

			model.addAttribute("adminID", adminObjExisting.getAdminID());

			return "adminProfile";
		} else {
			StoreAdmin adminOb = new StoreAdmin();
			model.addAttribute("AdminObj", adminOb);

			model.addAttribute("errormsg3", "In order to sign-in,you need to be registered with us");
			model.addAttribute("errormsg2", "--->>> If you have already registered with us");
			model.addAttribute("errormsg1", "--->>> PLEASE TRY SIGNING IN AGAIN WITH THE CORRECT EMAIL and PASSWORD");

			return "homepage";

		}
	}

	@GetMapping("/update")
	public String update(Model model, @ModelAttribute(value = "AdminObj") StoreAdmin adminUpdate) {
		storeService.updateAdmin(adminUpdate);
//model.addAttribute("data", data);
		return "adminProfile";
	}

	@GetMapping("/adminprof/{id}")
	public String admin(Model model, @ModelAttribute(value = "ourCare") StoreAdmin storeAdmin,
			@PathVariable(value = "id") int adminid) {
		StoreAdmin data = storeService.getAdmin(adminid);
		model.addAttribute("data", data);

		return "updateAdminProfile";
	}

	@RequestMapping("/adminLogout")
	public String logout(HttpSession session) {
	
		session.setAttribute("adminObj", null);
		log.debug("This is session object after "+session.getAttribute("adminObj"));
		return "redirect:/";

	}
	
	@RequestMapping(value="/forgotPasswordTwo", method=RequestMethod.GET)
	public ModelAndView UserforgotPasswordPageTwo(StoreAdmin storeAdmin) {
	System.out.println("entered into user/controller::::forgot password method");
	ModelAndView mav=new ModelAndView("forgotPasswordTwo");
	mav.addObject("AdminObj",storeAdmin);
	return mav;
	}
//for validating Email.
	@PostMapping(value ="/validateEmailTwo")
	public String checkMail(Model model,StoreAdmin storeAdmin) {
	System.out.println("entered into user/controller::::check Email existing or not");
	System.out.println("UI given mail Id:"+storeAdmin.getAdminEmail());
	StoreAdmin AdminObj=storeService.findbyEmail(storeAdmin.getAdminEmail());
	if(AdminObj!=null) {
	String s1="";
	model.addAttribute("message",s1);
	System.out.println("UI given mail Id:"+storeAdmin.getAdminEmail());
	model.addAttribute("AdminObj",storeAdmin);
	return "resetPasswordTwo";
	}
	else {
	System.out.println("Invalid Mail");
	String s1="Email-Id Not Exists";
	model.addAttribute("message",s1);
	model.addAttribute("AdminObj", new StoreAdmin());
	return "forgotPasswordTwo";
	}
	}
	//for reset password
	@RequestMapping(value = "/updateClientPasswordTwo", method = RequestMethod.POST)
	public ModelAndView updateUserPasswordTwo(Model model,@ModelAttribute("AdminObj") StoreAdmin storeAdmin) {
    StoreAdmin admin=storeService.findbyEmail(storeAdmin.getAdminEmail());
	System.out.println("inside updateClientPassword after update id is:::"+storeAdmin.getAdminID());
	String strEncPassword = Utilities.getEncryptSecurePassword(storeAdmin.getAdminPassword(),"GLAM");
	storeAdmin.setAdminPassword(strEncPassword);
	admin.setAdminPassword(storeAdmin.getAdminPassword());
	System.out.println(storeAdmin.getAdminPassword());
	System.out.println("in update method admin:: name "+admin.getAdminName());
	storeService.updateAdmin(admin);
	System.out.println("password is updated sucessfully");
	ModelAndView mav=new ModelAndView("homepage");
	System.out.println("login page is displayed");
	model.addAttribute("AdminObj",admin);
	return mav;
	}
	
}
