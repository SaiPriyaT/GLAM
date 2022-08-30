package com.glam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.glam.beans.Branch;
import com.glam.beans.CustomerAppointment;
import com.glam.beans.CustomerVisit;
import com.glam.beans.JobSeekers;
import com.glam.beans.JobVacancy;
import com.glam.beans.OurCare;
import com.glam.beans.Store;
import com.glam.repository.CustomerAppointmentRepository;
import com.glam.services.BranchService;
import com.glam.services.CustomerAppointmentService;
import com.glam.services.CustomerVisitService;
import com.glam.services.GlamService;
import com.glam.services.OurCareService;
import com.glam.services.StoreService;

@Controller
public class CustomerAppointmentController {
	@Autowired
	private OurCareService ourCareService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private GlamService glamService;
	@Autowired
	CustomerVisitService customerVisitService;
	@Autowired
	CustomerAppointmentRepository customerAppointmentRepository;
	@Autowired
	private CustomerAppointmentService customerAppointmentService;

	private static final Logger log = Logger.getLogger(CustomerAppointmentController.class);

	@GetMapping("/appointmentList")
	public String viewAllCusts(Model model, CustomerAppointment customerappointment) {
		model.addAttribute("appointmentList", customerAppointmentService.getAllAppointment());
		model.addAttribute("customerappointment", customerappointment);
		return "appointmentList";
	}

	@GetMapping("/review/{customerID}")
	public String review (Model model, @PathVariable("customerID") int customerID, Store store) {
		CustomerVisit customerVisit = customerVisitService.getCustomerVisitById(customerID);
		CustomerAppointment customerAppointment1 = new CustomerAppointment();
		List<Store> storeList = storeService.getAllStore();
		System.out.println("::::inside review method  calling::::");
		model.addAttribute("storeList", storeList);
		customerAppointment1.setCustomerID(customerID);
		model.addAttribute("customerAppointment1", customerAppointment1);
		model.addAttribute("customerID", customerID);
		model.addAttribute("customerVisit", customerVisit);
		return "customerAppointmentForm";
	}
	
	@PostMapping("/review/bookAppointment/save")
	public String bookAppointmentSave(@ModelAttribute("customerAppointment1") CustomerAppointment customerAppointment1,
			Model model, final HttpServletRequest request, HttpSession session) {
		customerAppointmentService.addNewCustomerAppointment(customerAppointment1);
		return "redirect:/review/bookAppointment/"+customerAppointment1.getAppointmentID();
	}
	@GetMapping("/review/bookAppointment/{customerAppID}")
	public String bookAppointmentSave(Model model,@PathVariable("customerAppID") int customerAppID){
		 CustomerAppointment customerAppointment1=customerAppointmentService.getCustomerAppointmentById(customerAppID);
		
		List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(customerAppointment1.getStoreID()));
		model.addAttribute("branchList", branchList);
		List<OurCare> ourCareList = ourCareService.listAllServices();
		System.out.println("ourCareList.get(0).getBranchID()" + ourCareList.get(0).getBranchID());
		List<OurCare> ourCare = new ArrayList<>();
		for (OurCare ourcare : ourCareList) {
			if (customerAppointment1.getBranchID() == ourcare.getBranchID()
					&& customerAppointment1.getStoreID() == ourcare.getStoreID()) {

				ourCare.add(ourcare);
				log.info(customerAppointment1);
			}
		}
		model.addAttribute("ourCare", ourCare);
		
		
		model.addAttribute("appointmentID",customerAppID);
		model.addAttribute("customerAppointment1", customerAppointment1);
		model.addAttribute("customerID", customerAppointment1.getCustomerID());
		model.addAttribute("customerAppointment", customerAppointment1);
		return "findServices";
	}
	
	@PostMapping("/review/bookAppointment")
	public String bookAppointment(@ModelAttribute("customerAppointment1") CustomerAppointment customerAppointment1,
			Model model, final HttpServletRequest request, HttpSession session) {

		List<Store> storeList = storeService.getAllStore();
		System.out.println("::::inside bookAppointment method calling::::");
		model.addAttribute("storeList", storeList);
		String getQry = request.getParameter("getQry");
		String storeID = request.getParameter("storeID");
		System.out.println(storeID + " " + getQry);
		if (getQry != null && getQry.equals("bookAppointmentjs") && storeID != null && storeID != "") {
			List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
			System.out.println("branchList:::" + branchList.size());
			model.addAttribute("branchList", branchList);
			System.out.println(storeID + " " + getQry + " hi");
			CustomerVisit customerVisit = customerVisitService
					.getCustomerVisitById(customerAppointment1.getCustomerID());
			model.addAttribute("customerAppointment1", customerAppointment1);
			model.addAttribute("customerID", customerAppointment1.getCustomerID());
			model.addAttribute("customerVisit", customerVisit);

			LocalDate sd = LocalDate.now();
			List<String> appointmentDate = new ArrayList<>();
			appointmentDate.add(sd.toString());
			for (int i = 1; i < 4; i++) {
				appointmentDate.add(sd.plusDays(i).toString());
			}
			model.addAttribute("appointmentDate", appointmentDate);
			return "customerAppointmentForm";
		}
		List<Branch> branchList = branchService
				.getAllBranchesByStoreID(Integer.valueOf(customerAppointment1.getStoreID()));
		model.addAttribute("branchList", branchList);

		/*
		 * customerAppointmentService.addNewCustomerAppointment(customerAppointment1);
		 */
		List<OurCare> ourCareList = ourCareService.listAllServices();
		System.out.println("ourCareList.get(0).getBranchID()" + ourCareList.get(0).getBranchID());
		List<OurCare> ourCare = new ArrayList<>();
		for (OurCare ourcare : ourCareList) {
			if (customerAppointment1.getBranchID() == ourcare.getBranchID()
					&& customerAppointment1.getStoreID() == ourcare.getStoreID()) {

				ourCare.add(ourcare);
				log.info(customerAppointment1);
			}
		}
		model.addAttribute("ourCare", ourCare);
		model.addAttribute("appointmentID", customerAppointment1.getAppointmentID());
		model.addAttribute("customerAppointment", customerAppointment1);
		return "findServices";
	}
	
	

	@PostMapping("/savecustomerAppointment/{appID}")
	public String savecustomerAppointment(Model model,String[] s1, @PathVariable("appID") int appID,@ModelAttribute("customerAppointment1")CustomerAppointment customerAppointment1) {
		
		Branch egbn=branchService.getBranchById(customerAppointment1.getBranchID());
		customerAppointment1.setBranchName(egbn.getBranchName());
		
		Store egsn=storeService.getStoreById(customerAppointment1.getStoreID());
		customerAppointment1.setStoreName(egsn.getStoreName());
		if(customerAppointment1.getCareID()==null) {
			List<Branch> branchList = branchService
					.getAllBranchesByStoreID(Integer.valueOf(customerAppointment1.getStoreID()));
			model.addAttribute("branchList", branchList);
			List<OurCare> ourCareList = ourCareService.listAllServices();
			System.out.println("ourCareList.get(0).getBranchID()" + ourCareList.get(0).getBranchID());
			List<OurCare> ourCare = new ArrayList<>();
			for (OurCare ourcare : ourCareList) {
				if (customerAppointment1.getBranchID() == ourcare.getBranchID()
						&& customerAppointment1.getStoreID() == ourcare.getStoreID()) {

					ourCare.add(ourcare);
					log.info(customerAppointment1);
				}
			}
			model.addAttribute("ourCare", ourCare);
			model.addAttribute("appointmentID", customerAppointment1.getAppointmentID());
			model.addAttribute("customerAppointment", customerAppointment1);
			model.addAttribute("customerappmsg","Please choose ATLEAST ONE service");
			return "findServices";
		}
		s1=customerAppointment1.getCareID().split(",");
		List<OurCare> ourcareList = new ArrayList<OurCare>();
		for(String cusapp : s1) {
			OurCare egon = ourCareService.getServiceByID(Integer.parseInt(cusapp));
			ourcareList.add(egon);
		}
		String sname2="";
		for(int i=0;i<ourcareList.size();i++) {
			String sname1=ourcareList.get(i).getCareName();
			if(i==ourcareList.size()-1) {
			sname2=sname2+sname1;
			}
			else {
				sname2=sname1+","+sname2;
			}
		}
		System.out.println("sname1.concat(sname1)="+sname2);
		customerAppointment1.setCareName(sname2);
		
		String sduration="";
		for(int i=0;i<ourcareList.size();i++) {
			int sduration1=ourcareList.get(i).getCareDuration();
			if(i==ourcareList.size()-1) {
				sduration=sduration+sduration1;
			}
			else {
				sduration=sduration1+","+sduration;
			}
		}
		customerAppointment1.setCareDuration(sduration);
		
		String sprice="";
		for(int i=0;i<ourcareList.size();i++) {
			double sprice1=ourcareList.get(i).getCarePrice();
			if(i==ourcareList.size()-1) {
				sprice=sprice+sprice1;
			}
			else {
				sprice=sprice1+","+sprice;
			}
		}
		customerAppointment1.setCarePrice(sprice);
		
		String stype="";
		for(int i=0;i<ourcareList.size();i++) {
			String stype1=ourcareList.get(i).getCareType();
			if(i==ourcareList.size()-1) {
				stype=stype+stype1;
			}
			else {
				stype=stype1+","+stype;
			}
		}
		customerAppointment1.setCareType(stype);
		
		String scategory="";
		for(int i=0;i<ourcareList.size();i++) {
			String scategory1=ourcareList.get(i).getCareCategory();
			if(i==ourcareList.size()-1) {
				scategory=scategory+scategory1;
			}
			else {
				scategory=scategory1+","+scategory;
			}
		}
		customerAppointment1.setCareCategory(scategory);
		customerAppointment1.setAppointmentBookedDate(LocalDate.now());

		customerAppointmentService.addNewCustomerAppointment(customerAppointment1);
		
		model.addAttribute("customerAppointment", customerAppointment1);
		model.addAttribute("customerID", customerAppointment1.getCustomerID());
		System.out.println(customerAppointment1.getCareID()+"    "+appID);
		model.addAttribute("appID", appID);
		
		return "BookingDetailsConfirmation";
	}


	
	@GetMapping("/appointmants/{id}")
	public String appointmentsByCustomerId(Model model, @PathVariable("id") int customerID) {

		List<CustomerAppointment> lists = customerAppointmentService.getAllAppointment();
		
	
		
		List<CustomerAppointment> list=lists.stream().filter(n->n.getCustomerID()==customerID).collect(Collectors.toList());
		System.out.println(list);
		model.addAttribute("list", list);
		return "customerBookedAppointments";

	}



@GetMapping("/appointmants")
public String getAppointments(Model model) {
	List<CustomerAppointment> list = customerAppointmentService.getAllAppointment();


	model.addAttribute("list", list);
	System.out.println(list);
	return "appointmentsList";
}}