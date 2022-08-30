package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.glam.beans.CustomerAppointment;
import com.glam.repository.CustomerAppointmentRepository;
@Service
public class CustomerAppointmentServiceImpl implements CustomerAppointmentService{
@Autowired
CustomerAppointmentRepository customerAppointmentRepository;
	
	@Override
	public CustomerAppointment addNewCustomerAppointment(CustomerAppointment customerAppointment) {
		return customerAppointmentRepository.save(customerAppointment);
	}

	@Override
	public void updateCustomerAppointment(CustomerAppointment customerAppointment) {
		customerAppointmentRepository.save(customerAppointment);
		
	}

	@Override
	public CustomerAppointment getCustomerAppointmentById(int appointmentID) {
	return customerAppointmentRepository.getById(appointmentID);

	}

	@Override
	public List<CustomerAppointment> getAllAppointment() {
		// TODO Auto-generated method stub
		return customerAppointmentRepository.findAll();
	}

	@Override
	public void deleteCustomerAppointmentById(int appointmentID) {
		try {
			customerAppointmentRepository.deleteById(appointmentID);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public void saveCustomerAppointment(CustomerAppointment customerAppointment) {
		this.customerAppointmentRepository.save(customerAppointment);
		
	}

}