package com.glam.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.Aboutus;
import com.glam.beans.Branch;
import com.glam.beans.Combo;
import com.glam.beans.EmployeeGeneral;
import com.glam.beans.JobVacancy;
import com.glam.beans.LatestNews;
import com.glam.beans.OurCare;
import com.glam.beans.SocialNetworks;
import com.glam.beans.Store;
import com.glam.repository.OurCareRepository;
import com.glam.services.AboutusService;
import com.glam.services.BranchService;
import com.glam.services.ComboService;
import com.glam.services.JobVacancyService;
import com.glam.services.LatestNewsService;
import com.glam.services.OurCareService;
import com.glam.services.SocialNetworkService;
import com.glam.services.StoreService;



@Controller
public class OurCareController {
	@Autowired
	private OurCareService ourCareService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private ComboService comboService;

	@Autowired
	private OurCareRepository ourCareRepository;

	@Autowired

	private AboutusService aboutusService;
	@Autowired
	
	private SocialNetworkService socialNetworkService;
	
	@Autowired
	
	private LatestNewsService lastestnewsService;
	
	@Autowired
	
	JobVacancyService jobVacancyService;
	
	private static final Logger log = Logger.getLogger(OurCareController.class);

	@GetMapping("/")
	public String homePage(Model model) {

		String ourCareString = "null";
		List<String> StringList = ourCareRepository.findDistinctCare(ourCareString);

		model.addAttribute("list", StringList);

		List<Aboutus> listAbout = aboutusService.getAllAbouts();

		model.addAttribute("aboutlist", listAbout);
		

		  List<SocialNetworks> networkList = socialNetworkService.getAllNetworks();
		  
		  model.addAttribute("networkList", networkList);
		  List <LatestNews> latestService=lastestnewsService.listAllNews();
		 
		  model.addAttribute("latestService", latestService);
		  List <JobVacancy> objPost=jobVacancyService.getAllJobs();
		  List<Store> storeList = storeService.getAllStore();
		  model.addAttribute("objStore", storeList);
			model.addAttribute("openingsList",objPost);
			List<Branch> branchList = branchService.getAllBranches();
			model.addAttribute("branches", branchList);

		return "homepage";

	}

	@GetMapping("/userservices/{careType}")
	public String services(Model model, @PathVariable("careType") String careType, OurCare ourCare) {

		List<OurCare> list = ourCareRepository.findAllByCareType(careType);

		model.addAttribute("careType", list);
		return "dynamicServices";

	}

	@GetMapping("/service")
	public String serviceList(Model model) {

		List<OurCare> ourCareList = ourCareService.listAllServices();
		log.debug("This is total list of services.");
		int count = ourCareList.size();
		List<OurCare> ourCare = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int careid = ourCareList.get(i).getCareID();
			OurCare care = ourCareService.getServiceByID(careid);
			Store store = storeService.getStoreById(care.getStoreID());
			care.setStoreName(store.getStoreName());
			ourCare.add(care);

		}

		model.addAttribute("ourCare", ourCareService.listAllServices());
		return "services";
	}

	@GetMapping("/service/{storeID}/{branchID}")
	public String servicesByStore(Model model, @PathVariable("storeID") int storeID,
			@PathVariable("branchID") int branchID) {
		log.debug("List of services in store id " + storeID + " and branch id " + branchID);
		List<OurCare> ourCareList = ourCareService.getAllServicesByStoreID(storeID, branchID);

		int count = ourCareList.size();
		List<OurCare> ourCare = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int careid = ourCareList.get(i).getCareID();
			OurCare care = ourCareService.getServiceByID(careid);
			Store store = storeService.getStoreById(care.getStoreID());
			Branch branch = branchService.getBranchById(care.getBranchID());
			care.setStoreName(store.getStoreName());
			care.setBranchName(branch.getBranchName());
           care.setCareName(care.getCareName());
			ourCare.add(care);

		}

		model.addAttribute("ourCare", ourCareService.getAllServicesByStoreID(storeID, branchID));
		return "services";
	}

	@GetMapping("/careTypeServices/{careType}")
	public String listByServiceType(Model model, @PathVariable("careType") String careType) {
		Map<Integer, String> CareTypeMap = dropDownCareType();
		model.addAttribute("CareMap", CareTypeMap);
		model.addAttribute("careID", CareTypeMap.keySet());
		List<OurCare> ourCareList = ourCareService.findByCareType(careType);
		model.addAttribute("ourCare", ourCareList);
		return "services";
	}

	@GetMapping("/addService")
	public String openServiceForm(Model model, HttpSession session) {
		session.setAttribute("updateSession", null);

		OurCare ourCare = new OurCare();

		model.addAttribute("ourCare", ourCare);

		model.addAttribute("storeList", storeService.getAllStore());

		return "addServiceForm";

	}
	
	@RequestMapping("/dropdownService")
	public String dropdownService(Model model,final HttpServletRequest request,@ModelAttribute("ourCare") OurCare ourCare) {
		

		List<Store> storeList = storeService.getAllStore();
		model.addAttribute("storeList", storeList);
		String getQry = request.getParameter("getQry");
		String storeID = request.getParameter("storeID");
		System.out.println(storeID + " " + getQry);
		if (getQry != null && getQry.equals("addService") && storeID != null && storeID != "") {
			List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(storeID));
			model.addAttribute("branchList", branchList);
			System.out.println(storeID + " " + getQry);

			return "addServiceForm";

		}
		List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(ourCare.getStoreID()));
		model.addAttribute("branchList", branchList);

		model.addAttribute("careID", ourCare.getCareID());
		model.addAttribute("ourCare", ourCare);
		

		return "";

	}

	@RequestMapping("/add")
	public String addService(@RequestParam("file") MultipartFile file, @ModelAttribute("ourCare") OurCare ourCare,
			Model model, final HttpServletRequest request, HttpSession session) throws IOException {
		
		if(file.getOriginalFilename()=="") {
			
			ourCare.setData(ourCare.getData());
			}
			else
			{
			ourCare.setData(Base64.getEncoder().encodeToString(file.getBytes()));

			}
		
ourCare.setIsActive('Y');

		if (ourCare.getCareID() == 0) {
			ourCare.setCreatedDate(LocalDateTime.now());
			ourCare.setUpdatedDate(LocalDateTime.now());

			ourCareService.addService(ourCare);

			System.out.println("This is add");
		} else {
			ourCare.setUpdatedDate(LocalDateTime.now());

			ourCareService.addService(ourCare);
			System.out.println("This is update");
			session.setAttribute("updateSession", null);
		}
		return "redirect:/service";
	}
	
	
	
		

	@GetMapping("/updateService/{careID}")
	public String updateService(@PathVariable int careID, Model model, HttpServletRequest request) {

		OurCare ourCare = ourCareService.getServiceByID(careID);

		log.debug("this is care id " + careID);

		log.debug("this is store id " + ourCare.getStoreID());

		HttpSession session = request.getSession();
		session.setAttribute("updateSession", "updateSession");

		model.addAttribute("ourCare", ourCare);

		List<Store> storeList = storeService.getAllStore();
		model.addAttribute("storeList", storeList);

		List<Branch> branchList = branchService.getAllBranchesByStoreID(Integer.valueOf(ourCare.getStoreID()));
		model.addAttribute("branchList", branchList);

		return "addServiceForm";

	}
	

	

	public Map<Integer, String> dropDownStore() {

		Map<Integer, String> StoreMap = new HashMap<Integer, String>();

		List<Store> storeList = storeService.getAllStore();
		for (Store objStore : storeList) {
			StoreMap.put(objStore.getStoreID(), objStore.getStoreName());
		}

		return StoreMap;
	}

	public Map<Integer, String> dropDownBranch() {

		Map<Integer, String> BranchMap = new HashMap<Integer, String>();

		List<Branch> branchList = branchService.getAllBranches();
		for (Branch objBranch : branchList) {
			BranchMap.put(objBranch.getBranchID(), objBranch.getBranchName());
		}

		return BranchMap;
	}

	public Map<Integer, String> dropDownCareType() {

		Map<Integer, String> CareTypeMap = new HashMap<Integer, String>();

		List<OurCare> ourCareList = ourCareService.listAllServices();
		for (OurCare objCare : ourCareList) {
			CareTypeMap.put(objCare.getCareID(), objCare.getCareType());
		}

		return CareTypeMap;
	}

	public Map<Integer, String> dropDownCombo() {

		Map<Integer, String> ComboMap = new HashMap<Integer, String>();

		List<Combo> comboList = comboService.getAllCombos();
		for (Combo objCombo : comboList) {
			ComboMap.put(objCombo.getComboID(), objCombo.getComboName());
		}

		return ComboMap;
	}

	@GetMapping("/deleteService/{careID}")
	public String deleteService(@PathVariable int careID) {

//			 ourCare.setIsActive('N');

		ourCareService.deleteServiceById(careID);

		return "redirect:/service";

	}


}