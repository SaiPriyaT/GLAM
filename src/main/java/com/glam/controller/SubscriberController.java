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

import com.glam.beans.JobVacancy;
import com.glam.beans.Store;
import com.glam.beans.Subscriber;
import com.glam.services.SubscriberService;

@Controller
public class SubscriberController {
	@Autowired
	private SubscriberService subscriberService;
	private static final Logger log = Logger.getLogger(StoreController.class);
	
	@GetMapping("/showNewSubscriberForm")
	public String showNewSubscriberForm(Model model) {
		Subscriber subscriber = new Subscriber();
		log.debug("This is a form for adding new subscriber.");
		model.addAttribute("objSubscriber", subscriber);
		return "newSubscriberForm";
	}
	@GetMapping("/subscribersList")
	public String subscriber(Model model) {
		log.debug("This is subscribers list");
	List<Subscriber> subscriberList = subscriberService.getAllsubscribers();
 		model.addAttribute("objSubscriber", subscriberList );
		return "subscribersList";
	}
	
	@PostMapping("/saveSubscriber")
	public String saveSubscriber(@ModelAttribute("objSubscriber") Subscriber subscriber) {
	String subscriptionType = subscriber.getSubscriptionType();
				
		subscriberService.savesubscriber(subscriber);
		
		return "redirect:/subscribersList";
	}

	
	@GetMapping("/subscriberUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
		Subscriber subscriber = subscriberService.getsubscriberById(id);
		System.out.println(subscriber+";;;;;;;;;;;;;;;;;;;;");
		model.addAttribute("subscriber", subscriber);
		return "updateSubscriber";
	}
	

}
