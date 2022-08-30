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
import org.springframework.web.bind.annotation.PostMapping;

import com.glam.beans.JobSeekers;
import com.glam.beans.JobVacancy;
import com.glam.services.JobSeekersService;
import com.glam.services.JobVacancyService;
@Controller
public class JobSeekersController {
	@Autowired 
	private JobSeekersService jobSeekersService;
	@Autowired 
	private JobVacancyService jobVacancyService;
	
	@GetMapping("/applicationList")
	public String viewAllApplications(Model model) {
		model.addAttribute("applicationslist",jobSeekersService.getAllApplications());
		return"applicationsList";
		}
	
	@GetMapping("/applicationList/{id}")
	public String getApplicationStringForOpenings(Model model, @PathVariable("id") int positionid) {
		List<JobSeekers> list = jobSeekersService.getAllApplicationsByPositionsID(positionid);

		int count = list.size();
		List<JobSeekers> obj = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			int jobseekerid = list.get(i).getJobSeekerID();
			JobSeekers objSeekers = jobSeekersService.getApplicationById(jobseekerid);
			JobVacancy jobVacancy = jobVacancyService.getPostById(objSeekers.getPositionID());
			objSeekers.setPositionName(jobVacancy.getPositionName());
			obj.add(objSeekers);

		}

		model.addAttribute("applicationList", obj);
		System.out.println(obj);
		return "applicationsList";
	}
	@PostMapping("/saveApplication")
    public String saveApplication(@ModelAttribute("application") JobSeekers jobSeekers,HttpServletRequest request, JobVacancy jobVacancy,Model model) {

    System.out.println("above::"+jobSeekers.getEmail());
    JobSeekers jobSeekerList=jobSeekersService.getJobSeekersListByEmailIgnoreCaseAndPositionID(jobSeekers.getEmail(), jobSeekers.getPositionID());
    System.out.println(jobSeekerList);
    if(jobSeekerList==null) {
    System.out.println("if condition");
    JobVacancy jobVacancy1 = jobVacancyService.getPostById(jobSeekers.getPositionID());
    jobSeekers.setPositionName(jobVacancy1.getPositionName());


    jobSeekersService.saveApplication(jobSeekers);
    JobVacancy list=jobVacancyService.getPostById(jobSeekers.getPositionID());
    model.addAttribute("Description",list);

    model.addAttribute("successMessage", "Congatulations...! Your application has been registered!!");
    return"jobDescription";


    //return "redirect:/jobDescription/" + jobVacancy.getPositionID();

    }
    else {
    JobVacancy list=jobVacancyService.getPostById(jobVacancy.getPositionID());

    model.addAttribute("Description",list);
    model.addAttribute("message", "You are applied for this job");
    return "jobDescription";
    }


    }
	
	@GetMapping("/showApplicationForm/{id}")
	public String showApplicationForm(Model model, @PathVariable("id") int positionID) {

		model.addAttribute("jobSeekers", new JobSeekers());
		model.addAttribute("positionID", positionID);

		return "applicationForm";
	}

	}	
	




