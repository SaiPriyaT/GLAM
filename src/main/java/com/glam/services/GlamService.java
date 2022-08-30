package com.glam.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.EmployeeAttendance;
import com.glam.beans.EmployeeDomain;
import com.glam.beans.EmployeeGeneral;

@Service
public interface GlamService {
//EmployeeGeneral Services//
	
List<EmployeeGeneral> getAllEmployees();	
EmployeeGeneral addEmployee(EmployeeGeneral empGeneral);
void updateEmployee(EmployeeGeneral empGeneral);
EmployeeGeneral getEmployee(String employeeEmailId,String employeePassword);
EmployeeGeneral getEmployeeById(int employeeId);
String getEmployeeEmailId(String employeeEmailId );
public List<EmployeeGeneral> getAllEmployeesByStoreIDandBranchID(int storeid,int branchid);
public List<EmployeeGeneral> getAllEmployeesByStoreIDBranchIDandDomainID(int storeid,int branchid,int domainid);
public List<EmployeeGeneral> getAllEmployeesByStoreID(int storeid);
void updateEmployeewithImage(MultipartFile file,EmployeeGeneral empGeneral) throws IOException;

//EmployeeDomain Services//
EmployeeDomain addDomain(EmployeeDomain empDomain);
EmployeeDomain getDomainById(int domainID);
void updateDomain(EmployeeDomain empDomain);
List<EmployeeDomain> getAllDomains();
public List<EmployeeDomain> getAllDomainsByStoreIDandBranchID(int storeid,int branchid);
public List<EmployeeDomain> getAllDomainsByStoreID(int storeid);
public List<EmployeeDomain> getAllDomainsBybranchID(int branchid);

//EmployeeAttendance Services//
EmployeeGeneral findbyEmail(String employeeEmailId);
EmployeeAttendance addAttendance(EmployeeAttendance empAttendance);
EmployeeAttendance getAttendanceById(int attendanceID);

}
