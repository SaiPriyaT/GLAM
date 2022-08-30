package com.glam.services;

import java.util.List;

import com.glam.beans.CustomerVisit;
    public interface CustomerVisitService {
	List<CustomerVisit> getAllCustomers();
	void deleteCustomerVisitById(int customerID);
    CustomerVisit addNewCustomerVisit(CustomerVisit customer);
	//void updateCustomerVisit(CustomerVisit customer);
    CustomerVisit updateCustomerVisit(CustomerVisit customer);
	CustomerVisit getCustomerVisitById(int customerID);
	CustomerVisit getCustomer(String customerEmailID,String password);
    void saveCustomers(CustomerVisit customervisit);
    String getEmail(String customerEmailID );
	CustomerVisit findbyEmail(String customerEmailID);
	
}