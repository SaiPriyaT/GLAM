package com.glam.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.EmployeeAttendance;
import com.glam.beans.EmployeeDomain;
import com.glam.beans.EmployeeGeneral;
import com.glam.repository.EmployeeAttendanceRepository;
import com.glam.repository.EmployeeDomainRepository;
import com.glam.repository.EmployeeGeneralRepository;



@Service
public class GlamServiceImpl implements GlamService {
	
	@Autowired
	private EmployeeGeneralRepository employeeGeneralRepository;
	
	@Autowired
	private EmployeeDomainRepository employeeDomainRepository;
	
	@Autowired
	private EmployeeAttendanceRepository employeeAttendanceRepository;
		
	@Override
	public List<EmployeeGeneral> getAllEmployees() {
		return employeeGeneralRepository.findAll();
		
	}

	@Override
	public EmployeeGeneral addEmployee(EmployeeGeneral empGeneral) {
		return employeeGeneralRepository.save(empGeneral);
		
	}
	
	@Override
	public void updateEmployee(EmployeeGeneral empGeneral) {
		
		employeeGeneralRepository.save( empGeneral);
		}
		
	

	@Override
	public EmployeeGeneral findbyEmail(String employeeEmailId) {
		// TODO Auto-generated method stub
		return employeeGeneralRepository.findByEmail(employeeEmailId);
	}
	

	@Override
	public EmployeeGeneral getEmployeeById(int employeeId) {
		return employeeGeneralRepository.getById(employeeId);
	}

	@Override
	public EmployeeGeneral getEmployee(String employeeEmailId, String employeePassword) {
		return employeeGeneralRepository.findByemployeeEmailIdandemployeePassword(employeeEmailId,employeePassword);
	}
	
	@Override
	public String getEmployeeEmailId(String employeeEmailId) {
		return employeeGeneralRepository.findByEmployeeEmailIdIgnoreCase(employeeEmailId);
	}

	@Override
	public List<EmployeeGeneral> getAllEmployeesByStoreIDandBranchID(int storeid, int branchid) {
		return employeeGeneralRepository.findAllEmployeesByStoreIDandBranchID(storeid, branchid);
	}
	
	@Override
	public List<EmployeeGeneral> getAllEmployeesByStoreIDBranchIDandDomainID(int storeid, int branchid, int domainid) {
		return employeeGeneralRepository.findAllEmployeesByStoreIDBranchIDandDomainID(storeid, branchid, domainid);
	}

	@Override
	public List<EmployeeGeneral> getAllEmployeesByStoreID(int storeid) {
		return employeeGeneralRepository.findAllEmployeesBystoreID(storeid)
				;
	}
	
	
	//EmployeeDomain ServiceImpl//
	
	@Override
	public EmployeeDomain addDomain(EmployeeDomain empDomain) {
		return employeeDomainRepository.save(empDomain);
	}

	@Override
	public EmployeeDomain getDomainById(int domainID) {
		return employeeDomainRepository.getById(domainID);
	}

	@Override
	public void updateDomain(EmployeeDomain empDomain) {
		employeeDomainRepository.save(empDomain);
		
	}

	@Override
	public List<EmployeeDomain> getAllDomains() {
		return employeeDomainRepository.findAll();
	}

	@Override
	public List<EmployeeDomain> getAllDomainsByStoreIDandBranchID(int storeid, int branchid) {
		return employeeDomainRepository.findAllDomainsByStoreIDandBranchID(storeid, branchid);
	}

	@Override
	public List<EmployeeDomain> getAllDomainsByStoreID(int storeid) {
		return employeeDomainRepository.findAllDomainsByStoreID(storeid);
	}

	@Override
	public List<EmployeeDomain> getAllDomainsBybranchID(int branchid) {
		return employeeDomainRepository.findAllDomainsByBranchID(branchid);
	}
	
	
	// EmployeeAttendance ServiceImpl //

	@Override
	public EmployeeAttendance addAttendance(EmployeeAttendance empAttendance) {
return employeeAttendanceRepository.save(empAttendance);	
}

	@Override
	public EmployeeAttendance getAttendanceById(int attendanceID) {
		return employeeAttendanceRepository.getById(attendanceID);
	}

	@Override
	public void updateEmployeewithImage(MultipartFile file, EmployeeGeneral empGeneral) throws IOException {
		
		employeeGeneralRepository.save(empGeneral);
	}

	

	
	

	

	
}