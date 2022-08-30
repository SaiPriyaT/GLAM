/*package com.glam.Project_GLAM;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.glam.beans.CustomerVisit;
import com.glam.repository.CustomerVisitRepository;
import com.glam.services.CustomerVisitServiceImpl;


@ExtendWith(MockitoExtension.class)
public  class CustomerVisitTest {
         @Mock
        private CustomerVisitRepository customerVisitRepository;
         @InjectMocks
        private CustomerVisitServiceImpl customerVisitServiceImpl;
        
         private CustomerVisit customerVisit;
        @BeforeEach
        public void test() {
           customerVisit= CustomerVisit.builder()
                    .customerID(1)
                    .customerName("saipriya")
                    .gender("female")
                    .customerLocation("karimnagar")
                    .build();
        }

        @Test
        public void testForSaveJs() {

        given(customerVisitRepository.save(customerVisit)).willReturn(customerVisit);

            System.out.println(customerVisitRepository);
            System.out.println(customerVisitServiceImpl);
            CustomerVisit savedJs =customerVisitServiceImpl.addNewCustomerVisit(customerVisit);

            System.out.println(savedJs);
            // then - verify the output
            assertThat(savedJs).isNotNull();
        }
         @DisplayName("JUnit test for getAllCustomers method (negative scenario)")
            @Test
        
            public void givenEmptyCustomerVisitList_whenGetAllCustomerVisit_thenReturnEmptyCustomerVisitList(){
                // given - precondition or setup

                 CustomerVisit customerVisit1 = CustomerVisit.builder().customerID(5).customerName("saipriya").gender("female").customerLocation("Karimnagar")


                        .build();
             System.out.println(";;;;;;"+customerVisit1);

                given(customerVisitRepository.findAll()).willReturn(Collections.emptyList());
                System.out.println("::::::"+Collections.emptyList());
                // when -  action or the behaviour that we are going test
              List<CustomerVisit> customerList =  customerVisitServiceImpl.getAllCustomers();

                // then - verify the output
                assertThat(customerList).isEmpty();
                assertThat(customerList.size()).isEqualTo(0);
            }
}*/
   /* @DisplayName("JUnit test for updateCustomerVisit method")
    @ParameterizedTest
    @ValueSource(strings = "updateCustomerVisit")
    @Test
       public void givenCustomerVisitUpdatedObject_whenUpdateCustomerVisit_thenReturnCustomerVisitUpdatedObject(CustomerVisit updateCustomerVisit){
         //  public void updateTest(CustomerVisit updateCustomerVisit) {
    	 // given - precondition or setup
         
    	 given(customerVisitRepository.save(customerVisit)).willReturn(customerVisit);
            customerVisit.setCustomerEmailID("swathi@gmail.com");
            customerVisit.setCustomerName("swathi");
            // when -  action or the behaviour that we are going test
          
            updateCustomerVisit = customerVisitServiceImpl.updateCustomerVisit(customerVisit);
          
            // then - verify the output
            assertThat(updateCustomerVisit.getCustomerEmailID()).isEqualTo("saipriyathallapelly2000@gmail.com");
            assertThat(updateCustomerVisit.getCustomerName()).isEqualTo("saipriya");
        }*/
     
     
     



     /* @DisplayName("JUnit test for deleteCustomerVisit method")
        @Test
        public void givenCustomerVisitId_whenDeleteCustomerVisit_thenNothing(){
            // given - precondition or setup
            int customerID = 1;

            willDoNothing().given(customerVisitRepository).deleteById(customerID);

            customerVisitServiceImpl.deleteCustomerVisitById(1);

            // then - verify the output
            verify(customerVisitRepository, times(1)).deleteById(customerID);
        }*/

         /*    @Test
         public void testForComparision() {
             CustomerVisit    customerVisit1 = CustomerVisit.builder()
                     .customerID(1)
                     .customerName("priya")
                     .gender("female")
                     .location("karimnagar")

                     .build();


         assertEquals(customerVisit, customerVisit1);
         Result result = assertEquals(customerVisit, customerVisit1);                    
         for (Failure failure : result.getFailures()) {                            
      System.out.println(failure.toString());                    
   }        
   System.out.println("Result=="+result.wasSuccessful());

         }*/



         /* @Test
          public void testForComparision() {
              CustomerVisit customerVisitone=CustomerVisit.builder()
                      .customerID(2)
                      .customerName("hemanth");
              .gender("male")
              .location("huzurabad")
              .build();
     assertEquals(customerVisit,customerVisitone);
     Result result= assertEquals(customerVisit,customerVisitone)
          }
      for(Failure failure:result.getFailure()) {
          System.out.println(failure.toString());
      }

 System.out.println("Result=="+result.wasSuccessful());

         }*/
         


