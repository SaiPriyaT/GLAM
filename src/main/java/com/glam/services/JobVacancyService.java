package com.glam.services;

import java.util.List;

import com.glam.beans.Branch;
import com.glam.beans.EmployeeGeneral;
import com.glam.beans.JobVacancy;

public interface JobVacancyService {

	List<JobVacancy> getAllJobs();

	JobVacancy saveJob(JobVacancy jobVacancy);

	JobVacancy getPostById(int positionid);

	public List<JobVacancy> getAllJobVacanciesByStoreID(int storeid);

	public List<JobVacancy> getAllJobVacanciesByStoreIDandBranchID(int storeid, int branchid);

	public List<JobVacancy> getAllJobVacanciesByStoreIDBranchIDandDomainID(int storeid, int branchid, int domainid);

	void deletePostById(int positionid);

}
