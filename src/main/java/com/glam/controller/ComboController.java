package com.glam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glam.beans.Combo;
import com.glam.beans.OurCare;
import com.glam.beans.Store;
import com.glam.services.ComboService;
import com.glam.services.OurCareService;
import com.glam.services.StoreService;

@Controller
public class ComboController {
	@Autowired
	private ComboService comboService;
	@Autowired
	private OurCareService ourCareService;
	@Autowired
	private StoreService storeService;

	@GetMapping("/home")
	public String newFile() {
		return "NewFile";
	}

	@GetMapping("/homeone")
	public String newFileone() {
		return "NewFileone";
	}

	@GetMapping("/combolist")
	public String getAllCombos(Model model) {
		List<Combo> list = comboService.getAllCombos();
		model.addAttribute("combolist", list);
		return "comboList";
	}
	 @GetMapping("/showNewComboForm")
		public String showNewStoreForm(Model model) {
			String ourCare=null;
			Combo combo = new Combo();
			model.addAttribute("objCombo", combo);
			List<String> careTypeList = ourCareService.getServicesByCareType(ourCare); 
			model.addAttribute("careTypeList", careTypeList); 
	     Map<Integer, String> StoreMap = dropDownStore();
			model.addAttribute("StoreMap", StoreMap);
			model.addAttribute("storeID", StoreMap.keySet());
			return "newComboForm";
		}
	 @GetMapping(value = "/getServices", produces =MediaType.APPLICATION_JSON_VALUE) 
	 public @ResponseBody List<OurCare>getServices(@RequestParam String careType) 
	 { 
		 List<OurCare> careTypeList =ourCareService.findByCareType(careType); 
		// log.warn("Care Type " +careType); 
		 System.out.println(careType);
		  return careTypeList;
	 }
	public Map<Integer, String> dropDownStore() {

		Map<Integer, String> StoreMap = new HashMap<Integer, String>();

		List<Store> storeList = storeService.getAllStore();
		for (Store objStore : storeList) {
			StoreMap.put(objStore.getStoreID(), objStore.getStoreName());
		}
    return StoreMap;
	}
	public Map<Integer, String> dropDownCombo() {

		Map<Integer, String> ComboMap = new HashMap<Integer, String>();

		List<Combo> comboList = comboService.getAllCombos();
		for (Combo objCombo : comboList) {
			ComboMap.put(objCombo.getComboID(), objCombo.getComboName());
		}

		return ComboMap;
	}

	public Map<Integer, String> dropDownCareType() {

		Map<Integer, String> CareTypeMap = new HashMap<Integer, String>();

		List<OurCare> ourCareList = ourCareService.listAllServices();
		for (OurCare objCare : ourCareList) {
			CareTypeMap.put(objCare.getCareID(), objCare.getCareType());
		}

		return CareTypeMap;
	}	


	 @GetMapping("/deleteCombo/{id}")
		public String deleteCombo(Model model,@PathVariable(value = "id") int comboID) {
			 comboService.deleteComboById(comboID);
			List<Combo> list = comboService.getAllCombos();
			model.addAttribute("combolist",list);
			model.addAttribute("msg","Deleted Successfully");
			return "comboList";
	       }

	@PostMapping("/saveCombo")
	public String saveCombo(Model model,@ModelAttribute("objCombo") Combo combo) {
		comboService.saveCombo(combo);
		List<Combo> list = comboService.getAllCombos();
		model.addAttribute("combolist", list);
		model.addAttribute("msg","Saved Successfully");
		return "comboList";
	}

@GetMapping("/updatedCombo/{id}")
	public String viewUpdateComboProfile(Model model,@PathVariable("id") int comboID, Combo combo) {
	model.addAttribute("objCombo", combo);
	model.addAttribute("objCombo", comboService.getComboById(comboID));
	model.addAttribute("comboID", comboService.getComboById(comboID).getComboID());
	return "updatecombo"; 
	}
 @PostMapping("/UploadComboProfile")
public String UploadComboProfile(Model model, Combo combo) {
	model.addAttribute("objCombo", combo);
	comboService.updateCombo(combo);
	model.addAttribute("combo", comboService.getAllCombos());
	List<Combo> list = comboService.getAllCombos();
	model.addAttribute("combolist", list);
	model.addAttribute("msg","Saved Successfully");
	return "comboList";
}


}