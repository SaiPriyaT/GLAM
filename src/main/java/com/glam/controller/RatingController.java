package com.glam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.glam.beans.Branch;
import com.glam.beans.CustomerVisit;
import com.glam.beans.EmployeeDomain;
import com.glam.beans.EmployeeGeneral;
import com.glam.beans.Rating;
import com.glam.beans.Store;
import com.glam.repository.EmployeeGeneralRepository;
import com.glam.repository.OurCareRepository;
import com.glam.repository.RatingRepository;
import com.glam.services.BranchService;
import com.glam.services.CustomerVisitService;
import com.glam.services.GlamService;
import com.glam.services.RatingService;
import com.glam.services.StoreService;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private CustomerVisitService customerVisitService;
	
	@Autowired
	private GlamService glamService;
	
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private OurCareRepository ourCareRepository;
	
	@Autowired
	private BranchService branchService;
	
	
	@Autowired
	private EmployeeGeneralRepository employeeGeneralRepository;
	
	
	@GetMapping("/viewEmployee/{customerID}/{employeeId}")
	public String viewRating(Model model, @PathVariable("customerID") int customerID, @PathVariable(value = "employeeId") int employeeId,
	Rating Emprating) {

	CustomerVisit customer = customerVisitService.getCustomerVisitById(customerID);
	model.addAttribute("customerID", customer.getCustomerID());
	EmployeeGeneral employee = glamService.getEmployeeById(employeeId);
	List<Rating> rating = ratingRepository.findAllRatingsInDescOrder();// doctorRatingService.GetAllDoctorRating();
	model.addAttribute("customerName", customer.getCustomerName());
	List<Rating> list = new ArrayList<>();
	for (Rating r : rating) {
	if (employee.getEmployeeId() == r.getEmployeeId()) {
	Rating rate = ratingService.getRatingById(r.getRatingID());
	list.add(rate);
	}
	}
	
	model.addAttribute("customer", customer);
	model.addAttribute("customerName", customer.getCustomerName());
	model.addAttribute("customerID", customer.getCustomerID());
	model.addAttribute("employeeId", employeeId);
	model.addAttribute("rating", list);
	model.addAttribute("Emprating", Emprating);
	model.addAttribute("employee", employee);
	double average;
	long totalrating;
	// Getting Average rating for doctor
	
	  if (ratingRepository.sumOfRating(employee.getEmployeeId()) == null ) {
	  totalrating = 0;
	  average = 0.0;
	  System.out.println("hi"+average);
	  model.addAttribute("avg", average);
	  }
	  else { 
		  totalrating =	  ratingRepository.sumOfRating(employee.getEmployeeId()); // Sum of ratings
	  
	  
	  
	  List<Rating> ratingCount = ratingRepository.emprating(employee.getEmployeeId()); // total entries 
	  long count = ratingCount.size();
	  // no.of patients given rating 
	  average = (totalrating /count);
	  System.out.println("total :"+totalrating);
	  System.out.println("count :"+count);
	  System.out.println("avg :"+average);
	  
	  model.addAttribute("average", Math.ceil(average)); 
	  }
	 
	// getting no.of persons according to rating
	int fivecount = ratingRepository.fivecount(employee.getEmployeeId());
	model.addAttribute("fivecount", fivecount);
	int fourcount = ratingRepository.fourcount(employee.getEmployeeId());
	model.addAttribute("fourcount", fourcount);
	int threecount = ratingRepository.threecount(employee.getEmployeeId());
	model.addAttribute("threecount", threecount);
	int twocount = ratingRepository.twocount(employee.getEmployeeId());
	model.addAttribute("twocount", twocount);
	int onecount = ratingRepository.onecount(employee.getEmployeeId());
	model.addAttribute("onecount", onecount);



	return "rating";



	}
	
	
	@PostMapping("/saveRating/{customerID}/{employeeId}")
public String saveRatings(@ModelAttribute("empRating")Rating rating ,@PathVariable("customerID") int customerID ,@PathVariable("employeeId") int employeeId){
		rating.setIsActive('Y');
		rating.setCreatedDate(LocalDate.now());
		ratingService.addNewRating(rating);
		
		return"redirect:/viewEmployee/"+customerID+"/"+employeeId;
	}
	
	
	@GetMapping("/selectStore/{customerID}")
	public String selectStore(Model model, @PathVariable("customerID") int customerID ) {
		
		CustomerVisit customer = customerVisitService.getCustomerVisitById(customerID);
		model.addAttribute("customerID", customer.getCustomerID());
		  List<Store> storeList = storeService.getAllStore();
		  model.addAttribute("objStore", storeList);
		
		return "storeAppointment";
	}
	
	
	@GetMapping("/selectStore/{id}/{customerID}")
	
	public String userBranchListByStoreID(Model model, @PathVariable("id") int storeid , @PathVariable("customerID") int customerID ) {

		CustomerVisit customer = customerVisitService.getCustomerVisitById(customerID);
		model.addAttribute("customerID", customer.getCustomerID());
		List<Branch> branchList = branchService.getAllBranchesByStoreID(storeid);
	//	log.debug("This is list of branches in store id " + storeid);
		int count = branchList.size();
		List<Branch> branchObj = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int branchid = branchList.get(i).getBranchID();
			Branch branch = branchService.getBranchById(branchid);
			Store store = storeService.getStoreById(branch.getStoreID());
			branch.setStoreName(store.getStoreName());
			branchObj.add(branch);

		}
		Store store = storeService.getStoreById(storeid);

		model.addAttribute("store", store);
		model.addAttribute("branchList", branchObj);
		return "branchAppointment";
	}

    @GetMapping("/selectBranch/{id}/{customerID}") 
	  public String selectDomain(Model model, @PathVariable("id") int  branchid, @PathVariable("customerID") int customerID) { 
    	CustomerVisit customer = customerVisitService.getCustomerVisitById(customerID);
    	model.addAttribute("customerID", customer.getCustomerID());
List<EmployeeDomain> employeeDomain =glamService.getAllDomainsBybranchID(branchid);
	  
	  
	//  Branch branch = branchService.getBranchById(branchid);
	//  log.debug("This is list of services in branch id " + branchid);

	  model.addAttribute("branch", employeeDomain); 
	  model.addAttribute("branchid", branchid); 
	  return "domianAppointment"; 
	  }
	
    @GetMapping("/selectDomain/{branchID}/{domainID}/{customerID}") 
	  public String selectEmployee(Model model , @PathVariable("customerID") int customerID, @PathVariable("branchID") int  branchid, @PathVariable("domainID") int  domainid) { 
List<EmployeeGeneral> employeeGeneral =employeeGeneralRepository.findAllEmployeesByDomainIDandBranchID(domainid,branchid);
CustomerVisit customer = customerVisitService.getCustomerVisitById(customerID);
model.addAttribute("customerID", customer.getCustomerID());
	  
	//  Branch branch = branchService.getBranchById(branchid);
	//  log.debug("This is list of services in branch id " + branchid);
	  model.addAttribute("branch", employeeGeneral); 
	  return "employeeAppointment"; 
	  }
    
    
    
	
}
