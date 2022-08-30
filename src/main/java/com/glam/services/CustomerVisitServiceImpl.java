package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.CustomerVisit;
import com.glam.repository.CustomerVisitRepository;
@Service
public class CustomerVisitServiceImpl implements CustomerVisitService{
@Autowired
private CustomerVisitRepository customerVisitRepository;
	
@Override
	public List<CustomerVisit> getAllCustomers() {
	return customerVisitRepository.findAll();
	}


	@Override
	public CustomerVisit addNewCustomerVisit(CustomerVisit customer) {
	
		return customerVisitRepository.save(customer);
		
	}

	/*@Override
	public void updateCustomerVisit(CustomerVisit customer) {
		this.customerVisitRepository.save(customer);
		
	}*/

	@Override
	public CustomerVisit getCustomerVisitById(int customerID) {

		return customerVisitRepository.findById(customerID).get();
	}
	


	@Override
	public CustomerVisit getCustomer(String customerEmailID, String password) {
		return customerVisitRepository.findByCustomeremailandpassword(customerEmailID, password);
	}

	@Override
	public void deleteCustomerVisitById(int customerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCustomers(CustomerVisit customervisit) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public String getEmail(String customerEmailID) {
		return customerVisitRepository.findByemailIgnoreCase(customerEmailID);
	
	}
	@Override
	public CustomerVisit findbyEmail(String customerEmailID) {
		// TODO Auto-generated method stub
		return customerVisitRepository.findByEmail(customerEmailID);
	}


	@Override
	public CustomerVisit updateCustomerVisit(CustomerVisit customer) {
		// TODO Auto-generated method stub
		
		return customerVisitRepository.save(customer);
	}


	public List<CustomerVisit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}