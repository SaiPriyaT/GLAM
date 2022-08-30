package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.JobVacancy;
import com.glam.repository.JobVacancyRepository;
@Service
public class JobVacancyServiceImpl implements JobVacancyService {

	
	@Autowired
	private JobVacancyRepository jobVacancyRepository;
	@Override
	public List<JobVacancy> getAllJobs() {
		// TODO Auto-generated method stub
		return jobVacancyRepository.findAll();
	}  

	@Override
	public JobVacancy saveJob(JobVacancy jobVacancy) {
		return jobVacancyRepository.save(jobVacancy);
	}
	@Override
	public void deletePostById(int positionid) {
		// TODO Auto-generated method stub
		this.jobVacancyRepository.deleteById(positionid);
		
	}
	@Override
	public JobVacancy getPostById(int positionid) {
		// TODO Auto-generated method stub
		return jobVacancyRepository.findById(positionid).get();
	}
	

	@Override
	public List<JobVacancy> getAllJobVacanciesByStoreID(int storeid) {
		// TODO Auto-generated method stub
		return jobVacancyRepository.findAllJobVacanciesBystoreID(storeid);
	}
	
	@Override
	public List<JobVacancy> getAllJobVacanciesByStoreIDandBranchID(int storeid, int branchid) {
		// TODO Auto-generated method stub
		return jobVacancyRepository.findAllJobVacanciesByStoreIDandBranchID(storeid,branchid);
	}
	
	@Override
	public List<JobVacancy> getAllJobVacanciesByStoreIDBranchIDandDomainID(int storeid, int branchid, int domainid) {
		// TODO Auto-generated method stub
		return jobVacancyRepository.findAllJobVacanciesByStoreIDBranchIDandDomainID(storeid,branchid,domainid);
	}

}
