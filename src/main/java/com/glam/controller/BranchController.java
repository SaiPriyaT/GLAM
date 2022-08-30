package com.glam.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.glam.beans.Branch;
import com.glam.beans.OurCare;
//import com.glam.beans.OurCare;
//import com.glam.beans.OurCare;
import com.glam.beans.Store;
import com.glam.repository.OurCareRepository;
//import com.glam.repository.BranchRepository;
import com.glam.services.BranchService;
import com.glam.services.OurCareService;
//import com.glam.services.OurCareService;
//import com.glam.services.OurCareService;
import com.glam.services.StoreService;

/* phani*/

@Controller
public class BranchController {

	@Autowired
	private BranchService branchService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private OurCareService ourCareService;
	@Autowired
	private OurCareRepository ourCareRepository;

	private static final Logger log = Logger.getLogger(BranchController.class);

	@GetMapping("/branchlist/{id}")
	public String branchListByStoreID(Model model, @PathVariable("id") int storeid) {

		List<Branch> branchList = branchService.getAllBranchesByStoreID(storeid);
		log.debug("This is list of branches in store id " + storeid);
		int count = branchList.size();
		List<Branch> branchObj = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int branchid = branchList.get(i).getBranchID();
			Branch branch = branchService.getBranchById(branchid);
			Store store = storeService.getStoreById(branch.getStoreID());
			branch.setStoreName(store.getStoreName());
			branchObj.add(branch);

		}
	


		model.addAttribute("brancheList", branchObj);
		model.addAttribute("storeid",storeid );
		return "branchList";
	}

	@GetMapping("/userstorelist/{id}")
	
	public String userBranchListByStoreID(Model model, @PathVariable("id") int storeid) {

		List<Branch> branchList = branchService.getAllBranchesByStoreID(storeid);
		log.debug("This is list of branches in store id " + storeid);
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
		return "storeDescription";
	}

    @GetMapping("/usersbranchlist/{storeID}/{branchID}") 
	  public String usersServiceListByBranchID(Model model, @PathVariable("storeID") int  storeID,@PathVariable("branchID") int branchID) { 
	
		List<String> StringList = ourCareRepository.findAllDistinctServicesByBranchID(storeID, branchID);

		model.addAttribute("list", StringList);

 	  
 	  
 	  
 	  
    	 Branch branch=branchService.getBranchById(branchID);
    	 model.addAttribute("branch",branch);
    //	 model.addAttribute("list",careObj);
    	 model.addAttribute("storeID",storeID);
    	 model.addAttribute("branchID",branchID);
    	 
    	 
    	 return "branchDescription"; 
    }
   
    
    @GetMapping("/userServiceName/{careType}/{storeID}/{branchID}")
    public String userServiceName(Model model,@PathVariable("storeID") int  storeID,@PathVariable("branchID") int  branchID ,@PathVariable("careType") String  careType) {
    	
    	List<OurCare> list =ourCareRepository.findAllByCareType(careType);
    	
    	model.addAttribute("careType", list);
    	return "dynamicServices";
    }
    
    
    
    
    	 @GetMapping("/branchlist")
	public String totalbranchList(Model model) {
		List<Branch> branchList = branchService.getAllBranches();
		log.debug("This is total list of branches. ");

		int count = branchList.size();
		List<Branch> branchObj = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int branchid = branchList.get(i).getBranchID();
			Branch branch = branchService.getBranchById(branchid);
			Store store = storeService.getStoreById(branch.getStoreID());
			branch.setStoreName(store.getStoreName());
			branchObj.add(branch);

		}
		model.addAttribute("brancheList", branchObj);
		return "branchList";
	}

	@GetMapping("/showNewBranchForm/{id}")
	public String showNewBranchForm(Model model, @PathVariable("id") int id) {
		Store store = storeService.getStoreById(id);
		Branch branch = new Branch();
		model.addAttribute("storeid", store.getStoreID());

		model.addAttribute("branch", branch);
		return "newBranchForm";
	}

	@PostMapping("/saveBranch")
	public String saveBranch(@ModelAttribute("branch") Branch branch) {
		branchService.saveBranch(branch);

		return "redirect:/branchlist/"+branch.getStoreID();

	}

	@GetMapping("/branchUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
		Branch branch = branchService.getBranchById(id);
		model.addAttribute("branch", branch);
		
		return "updateBranch";
	}

	@GetMapping("/deleteBranch/{id}")
	public String deleteBranch(@PathVariable(value = "id") int branchid) {
		this.branchService.deleteBranchById(branchid);

		return "redirect:/branchlist";
	}
}