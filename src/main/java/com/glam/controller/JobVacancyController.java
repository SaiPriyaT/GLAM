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
import com.glam.beans.JobVacancy;
import com.glam.beans.Store;
import com.glam.services.BranchService;
import com.glam.services.GlamService;
import com.glam.services.JobVacancyService;
import com.glam.services.StoreService;

@Controller
public class JobVacancyController {
	@Autowired
	JobVacancyService jobVacancyService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	GlamService glamService;
	
	
	
	@GetMapping("/jobDescription/{id}")
	public String description(Model model,@PathVariable("id")int positionID,JobVacancy jobVacancy) {
		JobVacancy list=jobVacancyService.getPostById(positionID);
		List <JobVacancy> vacancyList= jobVacancyService.getAllJobs();
		int count = vacancyList.size();
		List<JobVacancy> objOpeningsList= new ArrayList<>();
		for(int i=0;i<count;i++) {
			int positionID1=	vacancyList.get(i).getPositionID();
			JobVacancy jobVacancy1 = jobVacancyService.getPostById(positionID1);
			Store store = storeService.getStoreById(jobVacancy1.getStoreID());
			Branch branch = branchService.getBranchById(jobVacancy1.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(jobVacancy1.getDomainID());

			jobVacancy1.setStoreName(store.getStoreName());
			jobVacancy1.setBranchName(branch.getBranchName());
			jobVacancy1.setBranchName(branch.getBranchAddress());
			jobVacancy1.setDomainName(domain.getDomainName());
			objOpeningsList.add(jobVacancy1);
		}
		model.addAttribute("Description",list);
		return"jobDescription";
	}
	
	@GetMapping("/openingsList")
	public String openings(Model model) {
		List <JobVacancy> vacancyList= jobVacancyService.getAllJobs();
		int count = vacancyList.size();
		List<JobVacancy> objOpeningsList= new ArrayList<>();
		for(int i=0;i<count;i++) {
			int positionID=	vacancyList.get(i).getPositionID();
			JobVacancy jobVacancy = jobVacancyService.getPostById(positionID);
			Store store = storeService.getStoreById(jobVacancy.getStoreID());
			Branch branch = branchService.getBranchById(jobVacancy.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(jobVacancy.getDomainID());

//			JobVacancy post = jobVacancyService.getPostById(jobVacancy.getPositionID());
			jobVacancy.setStoreName(store.getStoreName());
			jobVacancy.setBranchName(branch.getBranchName());
			jobVacancy.setDomainName(domain.getDomainName());
			objOpeningsList.add(jobVacancy);
		
		}		
		model.addAttribute("objOpeningsList", jobVacancyService.getAllJobs());
		
		
		
		return "jobOpenings";
	}
	@GetMapping("/openingsList/{storeID}")
	public String openingsByStore(Model model,@PathVariable("storeID") int storeID) {
		List <JobVacancy> vacancyList= jobVacancyService.getAllJobVacanciesByStoreID(storeID);
		int count = vacancyList.size();
		List<JobVacancy> objOpeningsList= new ArrayList<>();
		for(int i=0;i<count;i++) {
			int positionID=	vacancyList.get(i).getPositionID();
			JobVacancy jobVacancy = jobVacancyService.getPostById(positionID);
			Store store = storeService.getStoreById(jobVacancy.getStoreID());
			Branch branch = branchService.getBranchById(jobVacancy.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(jobVacancy.getDomainID());

//			JobVacancy post = jobVacancyService.getPostById(jobVacancy.getPositionID());
			jobVacancy.setStoreName(store.getStoreName());
			jobVacancy.setBranchName(branch.getBranchName());
			jobVacancy.setDomainName(domain.getDomainName());
			objOpeningsList.add(jobVacancy);
		
		}		
		model.addAttribute("objOpeningsList", jobVacancyService.getAllJobVacanciesByStoreID(storeID));
		
		
		
		return "jobOpenings";
	}
	
	@GetMapping("/openingsList/{storeID}/{branchID}")
	public String openingsByStoreandBranch(Model model,@PathVariable("storeID") int storeID,@PathVariable("branchID") int branchID) {
		List <JobVacancy> vacancyList= jobVacancyService.getAllJobVacanciesByStoreIDandBranchID(storeID, branchID) ;
		int count = vacancyList.size();
		List<JobVacancy> objOpeningsList= new ArrayList<>();
		for(int i=0;i<count;i++) {
			int positionID=	vacancyList.get(i).getPositionID();
			JobVacancy jobVacancy = jobVacancyService.getPostById(positionID);
			Store store = storeService.getStoreById(jobVacancy.getStoreID());
			Branch branch = branchService.getBranchById(jobVacancy.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(jobVacancy.getDomainID());
			jobVacancy.setStoreName(store.getStoreName());
			jobVacancy.setBranchName(branch.getBranchName());
			jobVacancy.setDomainName(domain.getDomainName());
			objOpeningsList.add(jobVacancy);
		
		}		
		model.addAttribute("objOpeningsList", jobVacancyService.getAllJobVacanciesByStoreIDandBranchID(storeID, branchID));
		
				
		return "jobOpenings";
	}

	@GetMapping("/openingsList/{storeID}/{branchID}/{domainID}")
	public String openingsByStoreBranchandDomain(Model model,@PathVariable("storeID") int storeID,@PathVariable("branchID") int branchID, @PathVariable("domainID") int domainID) {
		List <JobVacancy> vacancyList= jobVacancyService.getAllJobVacanciesByStoreIDBranchIDandDomainID(storeID, branchID,domainID) ;
		int count = vacancyList.size();
		List<JobVacancy> objOpeningsList= new ArrayList<>();
		for(int i=0;i<count;i++) {
			int positionID=	vacancyList.get(i).getPositionID();
			JobVacancy jobVacancy = jobVacancyService.getPostById(positionID);
			Store store = storeService.getStoreById(jobVacancy.getStoreID());
			Branch branch = branchService.getBranchById(jobVacancy.getBranchID());
			EmployeeDomain domain = glamService.getDomainById(jobVacancy.getDomainID());
			jobVacancy.setStoreName(store.getStoreName());
			jobVacancy.setBranchName(branch.getBranchName());
			jobVacancy.setDomainName(domain.getDomainName());
			objOpeningsList.add(jobVacancy);
		
		}		
		model.addAttribute("objOpeningsList", jobVacancyService.getAllJobVacanciesByStoreIDBranchIDandDomainID(storeID, branchID, domainID));
		
				
		return "jobOpenings";
	}
	
	
	@GetMapping("/addNewJobForm")
	public String currentOpenings (Model model) {
		JobVacancy 		jobVacancy = new 		JobVacancy();
		model.addAttribute("objJob", jobVacancy);
		model.addAttribute("storeList", storeService.getAllStore());
		return "addJobForm";
}
	
	@RequestMapping("/saveJob")
	public String savePost(@ModelAttribute("objJob") JobVacancy jobVacancy, Model model,final HttpServletRequest request) {
		
		List<Store> storeList = storeService.getAllStore();
		model.addAttribute("storeList", storeList);
		String getQry =request.getParameter("getQry");
		String storeID =request.getParameter("storeID");
		System.out.println(storeID+" "+getQry);
		if(getQry !=null && getQry.equals("openingsList") && storeID !=null && storeID!="" ){
		List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
		model.addAttribute("branchList", branchList);
		System.out.println(storeID+" "+getQry);
		return "addJobForm";
		}
		List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(jobVacancy.getStoreID()));
		model.addAttribute("branchList", branchList);
		
		
		String getQrybranch = request.getParameter("getQrybranch");
		String branchID = request.getParameter("branchID");
		if(getQrybranch !=null && getQrybranch.equals("openingsListBranch") && branchID !=null && branchID !="") {
			List<EmployeeDomain> domainList = glamService.getAllDomainsBybranchID(Integer.valueOf(branchID));
			model.addAttribute("domainList", domainList);
				
			
			return "addJobForm";
		}

		List<EmployeeDomain> domainList =  glamService.getAllDomainsBybranchID(Integer.valueOf(jobVacancy.getBranchID()));
		model.addAttribute("domainList", domainList);
		
		Branch egbn=branchService.getBranchById(jobVacancy.getBranchID());
		jobVacancy.setBranchName(egbn.getBranchName());
		
		Store egsn=storeService.getStoreById(jobVacancy.getStoreID());
		jobVacancy.setStoreName(egsn.getStoreName());
		
		EmployeeDomain egdn = glamService.getDomainById(jobVacancy.getDomainID());
		jobVacancy.setDomainName(egdn.getDomainName());
		
		model.addAttribute("positionID", jobVacancy.getPositionID());
		model.addAttribute("objJob", jobVacancy);
		
	
		
		jobVacancyService.saveJob(jobVacancy);
		jobVacancy.setIsActive('Y');
		model.addAttribute("objOpeningsList", jobVacancyService.getAllJobs());
		
		
		model.addAttribute("msg","Saved Successfully");
		
			return "jobOpenings";

	}
	
	@GetMapping("/postUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
		JobVacancy jobVacancy = jobVacancyService.getPostById(id);
		List<Store> storeList = storeService.getAllStore();
		
		List<Branch> branchList =branchService.getAllBranchesByStoreID(Integer.valueOf(jobVacancy.getStoreID()));
		
		List<EmployeeDomain> domainList =  glamService.getAllDomainsBybranchID(Integer.valueOf(jobVacancy.getBranchID()));
		model.addAttribute("domainList", domainList);
		model.addAttribute("branchList", branchList);
		model.addAttribute("storeList", storeList);
		model.addAttribute("objJob", jobVacancy);
		return "AddJobForm";
	}
	
	@GetMapping("/deletePost/{id}")
	public String delete(Model model,@PathVariable(value = "id") int positionid) {
		this.jobVacancyService.deletePostById(positionid);
		
	model.addAttribute("objOpeningsList", jobVacancyService.getAllJobs());
		
		
	model.addAttribute("msg","Deleted Successfully");
	
		return "jobOpenings";

	}
}