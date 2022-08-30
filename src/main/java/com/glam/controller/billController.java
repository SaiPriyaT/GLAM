package com.glam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.glam.beans.Bills;
import com.glam.beans.CustomerAppointment;
import com.glam.beans.CustomerVisit;
import com.glam.beans.OurCare;
import com.glam.beans.Subscriber;
import com.glam.repository.BillRepository;
import com.glam.repository.CustomerAppointmentRepository;
import com.glam.repository.CustomerVisitRepository;
import com.glam.repository.OurCareRepository;
import com.glam.repository.SubscriberRepository;
import com.glam.services.BillService;
import com.glam.services.OurCareService;
import com.glam.services.SubscriberService;


@Controller

public class billController {
	@Autowired
	BillService billService;
	@Autowired
	CustomerAppointmentRepository customerAppointmentRepository;
	@Autowired
	OurCareRepository ourCareRepository;
	@Autowired
	OurCareService ourCareService;
	@Autowired
	BillRepository billRepository;

	
	  @Autowired 
	  SubscriberService subscriberService;
	  
	  @Autowired
	  CustomerVisitRepository customerVisitRepository;
	  
	  
	  
	  
	  @GetMapping(value="/bills/{appointmentID}")
	  public String  BillPage(@PathVariable("appointmentID")int appointmentID,Model model) {
	  CustomerAppointment appointment=customerAppointmentRepository.getById(appointmentID);
	  
	  String[] s1 = appointment.getCareID().split(",");
		List<OurCare> ourcareList = new ArrayList<OurCare>();
		double sum = 0;
		for(String cusapp : s1) {
 
	  OurCare care=ourCareService.getServiceByID(Integer.parseInt(cusapp));
	

	  sum=sum+care.getCarePrice();
	  

	  
	  Bills bills=new Bills();
	  bills.setBillID((int) Math.random());
	  bills.setBillAmount(care.getCarePrice());
	  bills.setCareDuration(care.getCareDuration());
	  bills.setCareName(care.getCareName());
	  bills.setCarePrice(care.getCarePrice()); 
	  bills.setCustomerID(appointment.getCustomerID());
	  bills.setCustomerName(appointment.getCustomerName());
	  bills.setAppointmentID(appointmentID);

	  bills.setStoreID(appointment.getStoreID());
	  
	  
	  Bills bill=billRepository.save(bills);
		
	
		}
		  CustomerVisit customerVisit=customerVisitRepository.getById(appointment.getCustomerID());
		  
		  
		  if(customerVisit.getSubscriberID()!=0) {
		  Subscriber subscriber=subscriberService.getsubscriberById(customerVisit.getSubscriberID());
		   
		  Double discount= (subscriber.getDiscount()/100.0)*sum;
		  Double gTotal=sum-discount;
		  Double serviceTax=(6/100.0)*gTotal;
		  Double cgst=(9/100.0)*gTotal;
		  Double sgst=(9/100.0)*gTotal;
		  Double total=gTotal+cgst+sgst+serviceTax;
		  model.addAttribute("gTotal", gTotal);
		  model.addAttribute("serviceTax", serviceTax);
		  model.addAttribute("cgst", cgst);
		  model.addAttribute("sgst", sgst);
		  model.addAttribute("SubscriptionDiscount", discount);
		  model.addAttribute("total", total);
	
		  }
		  else {
			 Double discount =(double) 0;
			  Double gTotal=sum-discount;
			  Double serviceTax=(6/100.0)*gTotal;
			  Double cgst=(9/100.0)*gTotal;
			  Double sgst=(9/100.0)*gTotal;
			  Double total=gTotal+cgst+sgst+serviceTax;
			  model.addAttribute("gTotal", gTotal);
			  model.addAttribute("serviceTax", serviceTax);
			  model.addAttribute("cgst", cgst);
			  model.addAttribute("sgst", sgst);
			  model.addAttribute("SubscriptionDiscount", discount);
			  model.addAttribute("total", total);
			
		}
	
		  
	  model.addAttribute("bills", billRepository.findBillsByAppointmentId(appointmentID));
	  model.addAttribute("appoinment",appointment);
	  System.out.println("--------------------"+appointment+"------------------------");
	  model.addAttribute("date",LocalDate.now());
	  return "cart";
	  }
	 
	@GetMapping("/addbills")
	public String showNewbills(Model model) {
		Bills news =new Bills();
	model.addAttribute("objnews",news);
	return "invoice-1";
	}
	
}
