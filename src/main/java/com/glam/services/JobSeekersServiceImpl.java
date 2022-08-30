package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.JobSeekers;
import com.glam.beans.JobVacancy;
import com.glam.repository.JobSeekersRepository;

@Service
public class JobSeekersServiceImpl implements JobSeekersService{
	@Autowired
	private JobSeekersRepository jobSeekersRepository;

	@Override
	public JobSeekers saveApplication(JobSeekers jobSeekers) {
		// TODO Auto-generated method stub
		return jobSeekersRepository.save(jobSeekers);
	}

	@Override
	public List<JobSeekers> getAllApplications() {
		// TODO Auto-generated method stub
		return jobSeekersRepository.findAll();
	}

	@Override
	public JobSeekers getJobSeekersListByEmailIgnoreCaseAndPositionID(String email, int positionID) {
	return jobSeekersRepository.getJobSeekersListByEmailIgnoreCaseAndPositionID(email, positionID);
	}


	@Override
	public void updateApplication(JobSeekers jobSeekers) {
		// TODO Auto-generated method stub
		this.jobSeekersRepository.save(jobSeekers);
	}

	@Override
	public List<JobSeekers> getAllApplicationsByPositionsID(int positionid) {
		// TODO Auto-generated method stub
		return jobSeekersRepository. findAllApplicationsByOpening(positionid);
	}

	@Override
	public JobSeekers getApplicationById(int jobseekerid) {
		// TODO Auto-generated method stub
		return jobSeekersRepository.findById(jobseekerid).get();
	}



	
	
	



	

}
