package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.Combo;
import com.glam.beans.OurCare;
@Repository

public interface ComboRepository extends JpaRepository<Combo, Integer> {
	@Query("select care from our_care care where care.careID=?1" )
	OurCare findByCareId(int careID);		
	@Query("SELECT care FROM our_care care")
	List<OurCare> findAllList();
	@Query("select distinct careType from our_care")
	List<String> findDistinctCare(String careType);
    @Query("select care from our_care care where care.careName=?1")
	List<OurCare> findService(String careName);
	}
