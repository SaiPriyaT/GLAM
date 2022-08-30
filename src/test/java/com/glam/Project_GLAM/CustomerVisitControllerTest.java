/*package com.glam.Project_GLAM;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.glam.beans.CustomerVisit;
import com.glam.controller.CustomerVisitController;
import com.glam.services.CustomerVisitService;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest(CustomerVisitController.class)

public class CustomerVisitControllerTest {

	@MockBean
	CustomerVisitService customerVisitService;
	
	@Autowired
	MockMvc mockMvc;
	@BeforeEach
 public void Before() {
		Mockito.when(customerVisitService.findAll()).thenReturn(getAllCustomers());
	}
	
	@Test
	 public List<CustomerVisit> getAllCustomers(){
		 List<CustomerVisit> list= new ArrayList<CustomerVisit>();
		 CustomerVisit customerVisit1= new CustomerVisit();
		 customerVisit1.setCustomerID(2);
		 customerVisit1.setCustomerName("saipriya");
		 
		 CustomerVisit customerVisit2= new CustomerVisit();
		 customerVisit2.setCustomerID(3);
		 customerVisit2.setCustomerName("priya");
		 list.add(customerVisit1);
		 list.add(customerVisit2);
		 
		return  list;
		 
	 }
	 @Test
	 public void testfindAll() throws Exception{
		 Mockito.when(customerVisitService.findAll()).thenReturn(getAllCustomers().get(0));
		 mockMvc.perform(get("/customerlist"))
		 //.andExpect(status().isOk());
		 .andExpect(jsonPath("$",Matchers.hasSize(2)))
		 .andExpect(jsonPath("$[0].customerName",Matchers.is("saipriya")))
		 .andExpect(jsonPath("$[1].customerName",Matchers.is("priya")));
	 }
	 @Test
	 public void testfindById() throws Exception{
	 Mockito.when(customerVisitService.findbyEmail(null)).thenReturn(getCustomerName().get(0));
		 mockMvc.perform(get("/homepage"))
		 //.andExpect(status().isOk());
		 .andExpect(jsonPath("$[0].customerName",Matchers.is("saipriya")))
		 .andExpect(jsonPath("$[1].customerName",Matchers.is("priya")));
	 }

 
	
}*/
