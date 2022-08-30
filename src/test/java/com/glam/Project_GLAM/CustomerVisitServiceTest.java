/*package com.glam.Project_GLAM;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import com.glam.beans.CustomerVisit;
import com.glam.repository.CustomerVisitRepository;
import com.glam.services.CustomerVisitService;

@ExtendWith(MockitoExtension.class)
public class CustomerVisitServiceTest {
@InjectMocks
CustomerVisitService customerVisitService;
@Mock
CustomerVisitRepository customerVisitRepository;
public List<CustomerVisit> getAllCustomers() {
	List<CustomerVisit> list=new ArrayList<CustomerVisit>();
	CustomerVisit customerVisit1= new CustomerVisit();
	customerVisit1.setCustomerID(1);
	customerVisit1.setCustomerName("priya");
	CustomerVisit customerVisit2= new CustomerVisit();
	customerVisit1.setCustomerID(2);
	customerVisit1.setCustomerName("sai");
	return list;
	
}
@Test
public void FindAllTest() {
	Mockito.when(customerVisitRepository.findAll()).thenReturn(getAllCustomers());
	List<CustomerVisit> customerVisit= customerVisitService.findAll();
	assertEquals(2,customerVisit.size());
	assertEquals("priya", customerVisit.get(0).getCustomerName());
	assertEquals("sai", customerVisit.get(0).getCustomerName());
	verify(customerVisitService, time(1)).findAll();
	
}

@Test
public void saveTest() {
	CustomerVisit customerVisit1= new CustomerVisit();
	
	customerVisit1.setCustomerName("priya");
	customerVisit1.setCustomerEmailID("saipriyathallapelly2000@gmail.com");
when(customerVisitService.saveCustomers(customerVisit1)).thenReturn(customerVisit1);
verify(customerVisitService,((CustomerVisitService) time(1)).saveCustomers(customerVisit1));

}
@Test
public void updateTest() {
	CustomerVisit customerVisit2= new CustomerVisit();
	
	customerVisit2.setCustomerName("priya");
	customerVisit2.setCustomerEmailID("saipriyathallapelly2000@gmail.com");
when(customerVisitService.updateCustomerVisit(customerVisit2)).thenReturn(customerVisit2);
verify(customerVisitService,((CustomerVisitService), time(1)).saveCustomers(customerVisit2));

}

}*/
