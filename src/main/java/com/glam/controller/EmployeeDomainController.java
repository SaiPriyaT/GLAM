package com.glam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glam.beans.Branch;
import com.glam.beans.EmployeeDomain;
import com.glam.beans.Store;
import com.glam.services.BranchService;
import com.glam.services.GlamService;
import com.glam.services.StoreService;


@Controller
public class EmployeeDomainController {

	@Autowired
	private GlamService glamService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private BranchService branchService;
	
	@GetMapping("/Domainlist/{storeID}")
	public String viewAlldomains(Model model,@PathVariable("storeID") int storeID) {
		
		List<EmployeeDomain> domainList = glamService.getAllDomainsByStoreID(storeID);
		
		int count = domainList.size();
		List<EmployeeDomain> empDomain = new ArrayList<>();
		for (int i=0; i<count; i++) {
			
			int domainID = domainList.get(i).getDomainID();
			EmployeeDomain empDomain1 = glamService.getDomainById(domainID);
			Store store = storeService.getStoreById(empDomain1.getStoreID());
			Branch branch = branchService.getBranchById(empDomain1.getBranchID());
			empDomain1.setStoreName(store.getStoreName());
			empDomain1.setBranchName(branch.getBranchName());
			
			empDomain.add(empDomain1);
			
			}
		
		model.addAttribute("DomainList",glamService.getAllDomainsByStoreID(storeID));
		return "domainList";
	}
	
	
	@GetMapping("/domains/{storeID}/{branchID}")
	public String domainsByStoreandBranch(Model model,@PathVariable("storeID") int storeID,@PathVariable("branchID") int branchID) {
		
		List<EmployeeDomain> domainList = glamService.getAllDomainsByStoreIDandBranchID(storeID, branchID);
		
		int count = domainList.size();
		List<EmployeeDomain> empDomain = new ArrayList<>();
		for (int i=0;i<count;i++) {
			
			int domainID = domainList.get(i).getDomainID();
			EmployeeDomain empDomain1 = glamService.getDomainById(domainID);
			Store store = storeService.getStoreById(empDomain1.getStoreID());
			Branch branch = branchService.getBranchById(empDomain1.getBranchID());
			empDomain1.setStoreName(store.getStoreName());
			empDomain1.setBranchName(branch.getBranchName());
			
			empDomain.add(empDomain1);
		}
		
		model.addAttribute("DomainList", glamService.getAllDomainsByStoreIDandBranchID(storeID, branchID));
		return "domainList";
	}



	
	
	@GetMapping("/domainForm")
	public String openServiceForm(Model model) {



	EmployeeDomain DomainObj = new EmployeeDomain();



	model.addAttribute("DomainObj", DomainObj);
	
	



	model.addAttribute("storeList", storeService.getAllStore());



	return "addDomainForm";



	}


	@RequestMapping("/addDomain")
	public String addService(@ModelAttribute("DomainObj") EmployeeDomain DomainObj, Model model,final HttpServletRequest request) {

	List<Store> storeList = storeService.getAllStore();
	model.addAttribute("storeList", storeList);
	String getQry =request.getParameter("getQry");
	String storeID =request.getParameter("storeID");
	System.out.println(storeID+" "+getQry);
	if(getQry !=null && getQry.equals("domainForm") && storeID !=null && storeID!="" ){
	List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
	model.addAttribute("branchList", branchList);
	System.out.println(storeID+" "+getQry);

	return "addDomainForm";

	}
	List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(DomainObj.getStoreID()));
	model.addAttribute("branchList", branchList);
    model.addAttribute("domainID", DomainObj.getDomainID());
	model.addAttribute("DomainObj", DomainObj);
glamService.addDomain(DomainObj);
model.addAttribute("DomainAdded", "The Domain has been added to the repsected store and branch provided");
	
	return "adminProfile";
	}
	



}
