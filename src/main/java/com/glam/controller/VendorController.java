package com.glam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glam.beans.Branch;
import com.glam.beans.Store;
import com.glam.beans.Vendors;
import com.glam.repository.BranchRepository;
import com.glam.repository.StoreRepository;
import com.glam.repository.VendorRepository;
import com.glam.services.BranchService;
import com.glam.services.StoreService;
import com.glam.services.VendorService;

@Controller
public class VendorController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private BranchService branchService;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private VendorService vendorService;

	@GetMapping("/vendorForm")
	public String VendorPage(Model model) {
		Vendors vendors = new Vendors();
		model.addAttribute("objVendors", vendors);
		List<Store> storeList = storeRepository.findAll();
		System.out.println(storeList);

		model.addAttribute("storeList", storeList);
		return "vendor";
	}

	@PostMapping("/saveVendor")
	public String saveVendor(Model model, @ModelAttribute("objVendors") Vendors vendors,
			final HttpServletRequest request, @RequestParam("branchID") int branchID) {
		model.addAttribute("objVendors", vendors);

		Branch branch = branchService.getBranchById(branchID);
		vendors.setBranchName(branch.getBranchName());
		vendors.setStoreName(branch.getStoreName());

		vendors.setBranchID(branchID);
		vendorRepository.save(vendors);

		return "redirect:/vendordetails";
	}

	@RequestMapping("/addVendors")
	public String branchListByStore(Model model, @ModelAttribute("objVendors") Vendors vendors,
			final HttpServletRequest request) {

		model.addAttribute("objVendors", vendors);

		List<Store> storeList = storeService.getAllStore();
		model.addAttribute("storeList", storeList);
		String getQry = request.getParameter("getQry");
		String storeID = request.getParameter("storeID");
		System.out.println("==================" + storeID + " " + getQry);
		if (getQry != null && getQry.equals("vendorForm") && storeID != null && storeID != "") {
			List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
			model.addAttribute("branchList", branchList);
			System.out.println(storeID + " " + getQry);

			return "vendor";

		}
		List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(vendors.getStoreID()));
		model.addAttribute("branchList", branchList);
		model.addAttribute("vendorID", vendors.getVendorID());
		model.addAttribute("Vendors", vendors);
		
		model.addAttribute("VendorAdded", "The Vendor has been added to the respected store and branch provided");

		return "vendor";
	}
	
	
	
	@GetMapping("/vendordetails")
    public String displayNews(Model model ,Vendors vendors) {
      //  model.addAttribute("Vendor", vendorService.listAllVendors());
        List<Vendors> vendorlist = vendorService.listAllVendors();
        System.out.println(vendorlist);
        model.addAttribute("Vendor", vendorlist);
        return "vendorlist";
        }

	/*
	 * @GetMapping("/venders/{storeID}/{branchID}") public String
	 * vendorsByStoreandBranch(Model model,@PathVariable("storeID") int
	 * storeID,@PathVariable("branchID") int branchID) {
	 * 
	 * 
	 * List<Vendors> vendorlist =
	 * vendorService.getAllVendorsByStoreIDandBranchID(storeID, branchID);
	 * 
	 * int count = vendorlist.size(); List<Vendors> vendors = new ArrayList<>(); for
	 * (int i=0;i<count;i++) {
	 * 
	 * int vendorID = vendorlist.get(i).getVendorID(); Vendors vendor
	 * =vendorService.getVendorId(vendorID); Store store =
	 * storeService.getStoreById(vendor.getStoreID()); Branch branch =
	 * branchService.getBranchById(vendor.getBranchID());
	 * vendor.setStoreName(store.getStoreName());
	 * vendor.setBranchName(branch.getBranchName());
	 * 
	 * vendors.add(vendor); }
	 * 
	 * model.addAttribute("DomainList",
	 * vendorService.getAllVendorsByStoreIDandBranchID(storeID, branchID)); return
	 * "vendorlist"; }
	 * 
	 * 
	 * @GetMapping("/vendorlist/{storeID}") public String viewAllvendors(Model
	 * model,@PathVariable("storeID") int storeID) {
	 * 
	 * List<Vendors> vendorlist = vendorService.getAllVendorsByStoreID(storeID);
	 * 
	 * int count = vendorlist.size(); List<Vendors> vendors = new ArrayList<>(); for
	 * (int i=0;i<count;i++) {
	 * 
	 * int vendorID = vendorlist.get(i).getVendorID(); Vendors vendor
	 * =vendorService.getVendorId(vendorID); Store store =
	 * storeService.getStoreById(vendor.getStoreID()); Branch branch =
	 * branchService.getBranchById(vendor.getBranchID());
	 * vendor.setStoreName(store.getStoreName());
	 * vendor.setBranchName(branch.getBranchName());
	 * 
	 * 
	 * 
	 * 
	 * vendors.add(vendor);
	 * 
	 * }
	 * 
	 * model.addAttribute("DomainList",vendorService.getAllVendorsByStoreID(storeID)
	 * ); return "domainList"; }
	 */

}
