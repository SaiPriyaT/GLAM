/*package com.glam.Project_GLAM;





import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.glam.beans.Store;
import com.glam.glam.ProjectGlamApplication;
import com.glam.repository.StoreRepository;

@SpringBootTest(classes = ProjectGlamApplication.class)
class ProjectGlamApplicationTests {

	
	@Test
	void contextLoads() {
	}
	@Autowired
	private StoreRepository storeRepository;
	// JUnit test for saveStore
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveStoreTest(){

    	Store store =Store.builder()
                .storeName("SR")
                .storeID(1)
                .appStoreLink("http://apps.apple.com")
                .storeAddress("Begumpet")
                .storeImage("download.jpg")
                .storedescription("This store will provid services 24/7 ")
                .description("This store is located at begumpet")
                .adminEmail("glamservices5@gmail.com")
                .adminMobile("7095533090")
                .storeLink("http://apps.google.com")
                .build();

    	storeRepository.save(store);

        Assertions.assertThat(store.getStoreID()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getStoreTest(){

        Store store = storeRepository.findById(1).get();

        Assertions.assertThat(store.getStoreID()).isEqualTo(1);

    }

      @Test
    @Order(4)
    @Rollback(value = false)
    public void updateStoreTest(){

        Store store = storeRepository.findById(1).get();

        store.setStoreName("GLAM");

        Store updateStore=  storeRepository.save(store);
        Assertions.assertThat(updateStore.getStoreName()).isEqualTo("GLAM");

      } 
        @Test
        @Order(3)
        public void getListOfStoresTest(){

            List<Store> store  = storeRepository.findAll();

            Assertions.assertThat(store.size()).isGreaterThan(0);

        }
     
  }*/