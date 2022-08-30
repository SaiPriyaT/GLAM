package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.JobSeekers;

@Repository
public interface JobSeekersRepository extends JpaRepository<JobSeekers, Integer> {

	@Query("select al FROM job_seekers al WHERE al.positionID =?1")
	List<JobSeekers> findAllApplicationsByOpening(int positionID);

	@Query(value ="SELECT a1 FROM job_seekers a1 WHERE lower(a1.email)=lower(?1) AND (a1.positionID)=?2")
	JobSeekers getJobSeekersListByEmailIgnoreCaseAndPositionID(String email,int positionID);
	}

