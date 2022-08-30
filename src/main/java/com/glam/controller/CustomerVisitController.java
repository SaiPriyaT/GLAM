package com.glam.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import org.springframework.web.servlet.ModelAndView;

//import org.springframework.web.bind.annotation.RequestMethod;
import com.glam.beans.CustomerVisit;
import com.glam.beans.Subscriber;
import com.glam.services.CustomerVisitService;
import com.glam.services.StoreService;
import com.glam.services.SubscriberService;
import com.glam.utils.Utilities;

@Controller

public class CustomerVisitController {
	@Autowired
	private CustomerVisitService customerVisitService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private SubscriberService subscriberService;

	@GetMapping("/customerlist")
	public String viewAllCusts(Model model, CustomerVisit customer) {
		model.addAttribute("customerlist", customerVisitService.getAllCustomers());
		model.addAttribute("customer", customer);
		return "customerList";
	}

	@GetMapping("/saveCustomer")
	public String CustomerRegistration(Model model,
			@ModelAttribute(value = "CustomerObject") CustomerVisit customervisit, HttpServletRequest request) {

		List<Subscriber> subscriber = subscriberService.getAllsubscribers();
		model.addAttribute("subscriber", subscriber);
		System.out.println(subscriber + ";;;;;;;;;;;;;;;;;;;;;;;");
//    customervisit.setCreatedDate(LocalDate.now());

		String existingcustomers = customerVisitService.getEmail(customervisit.getCustomerEmailID());

		if (existingcustomers != null) {
			System.out.println("cx already exists");

			CustomerVisit customervisitone = new CustomerVisit();
			model.addAttribute("CustomerObject", customervisitone);
			model.addAttribute("error", "you cannot register more than once,please sign-in to continue");

			return "homepage";
		} else {
			System.out.println("new registration");

			String strEncPassword = Utilities.getEncryptSecurePassword(customervisit.getPassword(), "GLAM");
			customervisit.setPassword(strEncPassword);
//		customervisit.setSubscriberID(1);

			CustomerVisit customer = customerVisitService.addNewCustomerVisit(customervisit);
			HttpSession session = request.getSession();
			session.setAttribute("CustomerObject", customer.getCustomerName());
			// session.setAttribute("CusObjId", cust.getCustomerID());

			model.addAttribute("customerID", customer.getCustomerID());

			System.out.println(customervisit.getCustomerName());

			model.addAttribute("CustomerObject", customervisit);

			return "addCustomerProfile";
		}
	}

	@RequestMapping("/customerVerification")
	public String validatemail(Model model, CustomerVisit customerVisit, final HttpServletRequest request,
			final HttpServletResponse response, HttpSession session) throws MessagingException {
//final StringBuffer uri = request.getRequestURL();

		model.addAttribute("Emailid", customerVisit.getCustomerEmailID());
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(customerVisit.getCustomerEmailID());

		mailMessage.setSubject("Complete Registration!");

		mailMessage.setFrom("glamservices5@gmail.com");

		mailMessage.setText("To confirm your account, please click here : " + session.getAttribute("url")
				+ "/saveCustomer?customerEmailID=" + customerVisit.getCustomerEmailID() + "&Password="
				+ customerVisit.getPassword() + "&customerMobile=" + customerVisit.getCustomerMobile()
				+ "&customerName=" + customerVisit.getCustomerName());

		storeService.sendSimpleEmail(customerVisit.getCustomerEmailID(),
				"To confirm your account, please click here : " + "http://localhost:9090/"
						+ "saveCustomer?customerEmailID=" + customerVisit.getCustomerEmailID() + "&Password="
						+ customerVisit.getPassword() + "&customerMobile=" + customerVisit.getCustomerMobile()
						+ "&customerName=" + customerVisit.getCustomerName(),
				"Email Verification");
//emailSenderService.sendEmail(mailMessage);

		model.addAttribute("Emailid", customerVisit.getCustomerEmailID());

		return "successfullyRegistered";

	}

	@PostMapping("/UploadCustomerProfile/{customerID}")
	public String UploadCustomerProfile(Model model,
			@ModelAttribute(value = "CustomerObject") CustomerVisit customervisit,
			@PathVariable("customerID") int customerID) {

		customerVisitService.getCustomerVisitById(customerID);
		customervisit.setSubscriptionDate(LocalDate.now());
		System.out.println("phani" + customervisit);
		customerVisitService.addNewCustomerVisit(customervisit);
		model.addAttribute("CustomerObject", customervisit);
		model.addAttribute("customerID", customervisit.getCustomerID());
		return "customerProfile";
	}

	@GetMapping("/sign-in")
	public String signinginUser(Model model, @ModelAttribute(value = "CustomerObject") CustomerVisit customerobjtwo,
			HttpServletRequest request) {

		String strEncPassword = Utilities.getEncryptSecurePassword(customerobjtwo.getPassword(), "GLAM");
		CustomerVisit CustomerObjExisting = customerVisitService.getCustomer(customerobjtwo.getCustomerEmailID(),
				strEncPassword);

		model.addAttribute("CustomerObject", CustomerObjExisting);
		model.addAttribute("hello", CustomerObjExisting);

		if (CustomerObjExisting != null) {
			HttpSession session = request.getSession();
			session.setAttribute("CustomerObject", CustomerObjExisting.getCustomerName());
			int customerID = CustomerObjExisting.getCustomerID();

			String subscribedDateString = CustomerObjExisting.getSubscriptionDate().toString();
			String currentDate = LocalDate.now().toString();
			LocalDate subscribedBefore = LocalDate.parse(subscribedDateString);

			LocalDate dateAfter = LocalDate.parse(currentDate);
			long noOfDaysBetween = ChronoUnit.DAYS.between(subscribedBefore, dateAfter);
			System.out.println(":::::::::::validity;;;;;;;;" + CustomerObjExisting.getValidity());
			if (noOfDaysBetween >= CustomerObjExisting.getValidity()) {
				CustomerObjExisting.setSubscriberID(0);
				customerVisitService.addNewCustomerVisit(CustomerObjExisting);

			}

			model.addAttribute("customerID", customerID);
			model.addAttribute("name1", CustomerObjExisting.getCustomerName());

			return "customerProfile";
		} else {
			CustomerVisit customerobjthree = new CustomerVisit();
			model.addAttribute("CustomerObject", customerobjthree);

			model.addAttribute("errormsg3", "In order to sign-in,you need to be registered with us");
			model.addAttribute("errormsg2", "--->>> If you have already registered with us");
			model.addAttribute("errormsg1", "--->>> PLEASE TRY SIGNIGN IN AGAIN WITH THE CORRECT EMAIL and PASSWORD");

			return "homepage";
		}
	}

	@GetMapping("/subscription/{customerID}")
	public String subscription(Model model, @PathVariable("customerID") int customerID) {
		List<Subscriber> subscriber = subscriberService.getAllsubscribers();
		model.addAttribute("subscriber", subscriber);
		// System.out.println(subscriber+";;;;;;;;;;;;;;;;;;;;;;;");
		// System.out.println(customerVisit+";;;;;;;;customerVisit;;;;;;;;;;;;;;;");
		CustomerVisit customerVisit = customerVisitService.getCustomerVisitById(customerID);
		// System.out.println(customerVisit+";;;;;;;;customerVisit;;;;;;;;;;;;;;;");
		model.addAttribute("CustomerObject", customerVisit);
		customerVisit = customerVisitService.getCustomerVisitById(customerID);
		model.addAttribute("sub", customerVisit.getSubscriberID());

		model.addAttribute("CustomerObject", customerVisit);
		model.addAttribute("customerID", customerVisitService.getCustomerVisitById(customerID).getCustomerID());
		return "userSubscriptionForm";
	}

	@PostMapping("/subscriptionPayment/{customerID}")
	public String subscriptionPayment(Model model,
			@ModelAttribute(value = "CustomerObject") CustomerVisit customervisit,
			@PathVariable("customerID") int customerID, Subscriber subscriber) {

		System.out.println("payment customer visit;;;;;;;;;;;;,;,;,;;,;;;........" + customervisit);

		CustomerVisit customer = customerVisitService.getCustomerVisitById(customerID);
		subscriber = subscriberService.getsubscriberById(customervisit.getSubscriberID());
		System.out.println("customer;;;sub;;;;;;;;;;;'''''''''''''''''''''',,,,," + customer);
//     customerVisitService.addNewCustomerVisit(customervisit);
		model.addAttribute("CustomerObject", customer);
		model.addAttribute("subscriber", subscriber);
		model.addAttribute("customerID", customer.getCustomerID());
		return "subscriptionPayment";
	}

	@PostMapping("/savePayment/{customerID}/{subscriberID}")
	public String savePayment(Model model, @ModelAttribute(value = "CustomerObject") CustomerVisit customervisit,
			@PathVariable("customerID") int customerID, @PathVariable("subscriberID") int subscriberID) {
		// System.out.println("0000000000000000"+customervisit.getCreatedDate());

		customerVisitService.getCustomerVisitById(customerID);
		customervisit.setSubscriptionDate(LocalDate.now());
		customervisit.setSubscriberID(subscriberID);
		Subscriber subscriber = subscriberService.getsubscriberById(subscriberID);

		customervisit.setValidity(subscriber.getValidity());

		customerVisitService.addNewCustomerVisit(customervisit);
		System.out.println(customervisit.getSubscriberID() + ";;;;;;;;;;;;");
		model.addAttribute("subscriptionPopup", customervisit.getSubscriberID());

		model.addAttribute("CustomerObject", customervisit);
		model.addAttribute("customerID", customervisit.getCustomerID());
		return "customerProfile";
	}

	@GetMapping("/updatedProfile/{customerID}")
	public String viewUpdateEmpProfile(Model model, @PathVariable("customerID") int customerID,
			CustomerVisit customerVisit) {

		List<Subscriber> subscriber = subscriberService.getAllsubscribers();
		model.addAttribute("subscriber", subscriber);
		System.out.println(subscriber + ";;;;;;;;;;;;;;;;;;;;;;;");

		model.addAttribute("CustomerObject", customerVisit);

		model.addAttribute("CustomerObject", customerVisitService.getCustomerVisitById(customerID));
		model.addAttribute("customerID", customerVisitService.getCustomerVisitById(customerID).getCustomerID());
		return "addCustomerProfile";
	}

	@RequestMapping("/customerLogout")
	public String logout(HttpSession session) {
		session.setAttribute("CustomerObject", null);
		System.out.println(session.getAttribute("CustomerObject"));
		return "redirect:/";

	}

	//for forgotpassword.
		@RequestMapping(value="/forgotPassword", method=RequestMethod.GET)
		public ModelAndView UserforgotPasswordPage(CustomerVisit customerVisit) {
		System.out.println("entered into user/controller::::forgot password method");
		ModelAndView mav=new ModelAndView("forgotPassword");
		mav.addObject("CustomerObject", customerVisit);
		return mav;
		}


	//for validating Email.
		@PostMapping(value ="/validateEmail")
		public String checkMail(Model model,CustomerVisit customerVisit) {
		System.out.println("entered into user/controller::::check Email existing or not");
		System.out.println("UI given mail Id:"+customerVisit.getCustomerEmailID());
		CustomerVisit CustomerObject= customerVisitService.findbyEmail(customerVisit.getCustomerEmailID());
		
		if(CustomerObject!=null) {
		String s1="";
		model.addAttribute("message",s1);
		model.addAttribute("CustomerObject",CustomerObject);
		System.out.println(CustomerObject);

		return "resetPassword";
		}
		else {
		System.out.println("Invalid Mail");
		String s1="Email-Id Not Exists";
		model.addAttribute("message",s1);
		model.addAttribute("CustomerObject", new CustomerVisit());
		return "forgotPassword";
		}
		}
		

	//for reset password
		@RequestMapping(value = "/updateClientPassword", method = RequestMethod.POST)
		public ModelAndView updateUserPassword(Model model,@ModelAttribute("CustomerObject") CustomerVisit customerVisit) {
			System.out.println("inside updateClientPassword after update id is:::"+customerVisit);
		CustomerVisit customer=customerVisitService.findbyEmail(customerVisit.getCustomerEmailID());
		System.out.println("inside updateClientPassword after update id is:::"+customer);

		String strEncPassword = Utilities.getEncryptSecurePassword(customerVisit.getPassword(),"GLAM");
		customerVisit.setPassword(strEncPassword);
		customer.setPassword(customerVisit.getPassword());
		//System.out.println(customerVisit.getPassword());
		//System.out.println(" in update method Client created date::"+customer.getCreated());
		System.out.println("in update method customer:: name "+customer);
		
		customerVisitService.updateCustomerVisit(customer);
		//customer.setUpdatedDate(LocalDateTime.now());
		System.out.println("password is updated sucessfully");
		ModelAndView mav=new ModelAndView("homepage");
		System.out.println("login page is displayed");
		model.addAttribute("CustomerObject", customer);
		return mav;
		}
	}
