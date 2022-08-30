package com.glam.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.glam.beans.CustomerAppointment;

public interface CustomerAppointmentService {
//List<CustomerAppointment> getAllCustomer();

CustomerAppointment addNewCustomerAppointment(CustomerAppointment customerAppointment);
void updateCustomerAppointment(CustomerAppointment customerAppointment);
CustomerAppointment getCustomerAppointmentById(int appointmentID);
void deleteCustomerAppointmentById(int appointmentID);
List<CustomerAppointment> getAllAppointment();
//List<CustomerAppointment> getAllAppointmentsById(int customerID);
void saveCustomerAppointment(CustomerAppointment customerAppointment);

}