package com.glam.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.glam.beans.Branch;
import com.glam.beans.EmployeeAttendance;
import com.glam.beans.EmployeeDomain;
import com.glam.beans.Store;
import com.glam.services.BranchService;
import com.glam.services.GlamService;
import com.glam.services.StoreService;

@Controller
public class EmployeeAttendanceController {
	
	@Autowired
	private GlamService glamService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private BranchService branchService;
	
	
    @GetMapping("/preAttendanceForm/{empID}")
    public String openpreAttendzform(Model model, EmployeeAttendance empAttendance,@PathVariable("empID") int empID) {
    	
    	model.addAttribute("EmpAttendance", empAttendance);
    	

    	model.addAttribute("EmpAttendance", glamService.getEmployeeById(empID));
    	
    	
    	
    	
    	return "preAttendForm";
    }
    
    @PostMapping("/CheckIN")
    public String checkINbutton(@ModelAttribute("EmpAttendance") EmployeeAttendance attendanceObj, Model model,HttpSession session) {
    	
    	
    	attendanceObj.setCheckIn(LocalTime.now());
    	
    	attendanceObj.setDate(LocalDate.now());
    	
    	Branch eabn=branchService.getBranchById(attendanceObj.getBranchID());
    	attendanceObj.setBranchName(eabn.getBranchName());
		
		Store easn=storeService.getStoreById(attendanceObj.getStoreID());
		attendanceObj.setStoreName(easn.getStoreName());
		
		EmployeeDomain eadn = glamService.getDomainById(attendanceObj.getDomainID());
		attendanceObj.setDomainName(eadn.getDomainName());
		
    	
    	EmployeeAttendance attendanceObj1 = glamService.addAttendance(attendanceObj);
    	
    	model.addAttribute("attendaceID",attendanceObj1.getAttendaceID());
    	
    	model.addAttribute("EmpAttendance", attendanceObj1);
    	
    	
   
    	return "postCheckInForm";
    	
    }
   
    
    @PostMapping("/CheckOut/{attendaceID}")
    public String CheckOUTbutton(@PathVariable("attendaceID") int attendaceID,Model model) {
    	
    	
    	EmployeeAttendance attendanceObj=glamService.getAttendanceById(attendaceID);
    	
    	attendanceObj.setCheckOut(LocalTime.now());
    	
    	EmployeeAttendance attendanceObj1 = glamService.addAttendance(attendanceObj);
    	
    	model.addAttribute("EmpAttendance", attendanceObj1);
    	
    	return "postCheckInForm";
    	
    	
    }
    
    

}
