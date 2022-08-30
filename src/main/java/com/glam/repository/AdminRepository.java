package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.glam.beans.StoreAdmin;
@Repository
public interface AdminRepository extends JpaRepository<StoreAdmin, Integer> {
	
	@Query("select al from store_admin al where lower(al.adminEmail) = lower(?1) and al.adminPassword=?2 ")
	StoreAdmin findByEmailIgnoreCaseAndPassword(String adminEmail, String adminpassword);

	@Query("select al.adminEmail from store_admin al where al.adminEmail=?1")
	String findByStoreAdminEmailIdIgnoreCase(String adminEmail);
	
	@Query("select a from store_admin a WHERE lower(a.adminEmail)=lower(?1)")
	public StoreAdmin findByEmail(String adminEmail);
}
