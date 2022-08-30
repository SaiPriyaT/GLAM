package com.glam.services;

import java.util.List;

import com.glam.beans.JobSeekers;

public interface JobSeekersService {
	JobSeekers saveApplication(JobSeekers jobSeekers);

	List<JobSeekers> getAllApplications();
	public List<JobSeekers> getAllApplicationsByPositionsID(int positionid);



	JobSeekers getApplicationById(int jobseekerid);
	
	JobSeekers getJobSeekersListByEmailIgnoreCaseAndPositionID(String email,int positionID);
	void updateApplication(JobSeekers jobSeekers);

}
