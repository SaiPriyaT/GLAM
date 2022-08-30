package com.glam.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.Store;
import com.glam.services.StoreService;

@Controller

public class StoreController {
	@Autowired
	private StoreService service;

	private static final Logger log = Logger.getLogger(StoreController.class);



	@GetMapping("/showNewStoreForm")
	public String showNewStoreForm(Model model) {
		Store store = new Store();
		log.debug("This is a form for adding new store.");
		model.addAttribute("objStoreTwo", store);
		return "newStoreForm";
	}
	@GetMapping("/storeList")
	public String stores(Model model) {
		log.debug("This is store list");
	List<Store> storeList = service.getAllStore();
 		model.addAttribute("objStore", storeList );
		return "storesList";
	}
	@PostMapping("/saveStore")
	public String saveStore(Model model,@ModelAttribute("objStore") Store store,@RequestParam("fileImg") MultipartFile Imagefile,@RequestParam("fileVid") MultipartFile Videofile) {
	service.addStorewithImageVideos(Imagefile, Videofile, store);
	List<Store> storeList = service.getAllStore();
		model.addAttribute("objStore", storeList );
		model.addAttribute("msg","Saved Successfully" );
		return "storesList";
	}

	@GetMapping("/storeUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value = "id") int storeId, Model model) {
		Store store = service.getStoreById(storeId);
		log.debug("This is update form for store.");
		model.addAttribute("store", store);
		model.addAttribute("storeID", store.getStoreID());
		return "updateStore";
	}

	@GetMapping("/deleteStore/{id}")
	public String deleteStore(@PathVariable(value = "id") int storeid) {
		this.service.deleteStoreById(storeid);
		return "redirect:/storeList";

	}
	
	 @GetMapping("/Description/{id}")
		public String description(Model model,@PathVariable("id")int storeID,Store store) {
			Store list=service.getStoreById(storeID);
			List <Store> storeList= service.getAllStore();
					model.addAttribute("objStore", storeList);
			 model.addAttribute("Description",list);
			return"storeDescription";
		}

}