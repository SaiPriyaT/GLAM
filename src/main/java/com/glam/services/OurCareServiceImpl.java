package com.glam.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.Combo;
import com.glam.beans.OurCare;
import com.glam.repository.OurCareRepository;
@Service
public class OurCareServiceImpl implements OurCareService{
@Autowired
private OurCareRepository ourCareRepository;


	@Override
	public OurCare getServiceByID(int careID) {
		// TODO Auto-generated method stub
		return ourCareRepository.findByCareId(careID);
	}

	@Override
	public OurCare addService(OurCare ourCare) {
		// TODO Auto-generated method stub
		return ourCareRepository.save(ourCare);
	}

	
	@Override
	public void updateService(OurCare ourCare) {
		// TODO Auto-generated method stub
		this.ourCareRepository.save(ourCare);
	}

	@Override
	public List<OurCare> findByCareType(String careType) {
		// TODO Auto-generated method stub
		return ourCareRepository.findAllByCareType(careType);
	}

//	@Override
//	public List<OurCare> findByKeyword(String keyword) {
//		// TODO Auto-generated method stub
//		return ourCareRepository.findByKeyword(keyword);
//	}

	@Override
	public List<OurCare> listAllServices() {
		// TODO Auto-generated method stub
		return ourCareRepository.findAllList();
	}

	@Override
	public void deleteServiceById(int careID) {
		// TODO Auto-generated method stub
		this.ourCareRepository.deleteById(careID);
	}

	@Override
	public List<OurCare> getAllServicesByStoreID(int storeid, int branchid) {
		// TODO Auto-generated method stub
		return ourCareRepository.findAllServicesByStoreID(storeid, branchid);
	}

	@Override
	public void addService(MultipartFile file, OurCare ourCare)
	{

	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	if(fileName.contains(".."))
	{
	System.out.println("not a a valid file");
	}
	try {
	ourCare.setData(Base64.getEncoder().encodeToString(file.getBytes()));

	} catch (IOException e) {
	e.printStackTrace();
	}
	ourCareRepository.save(ourCare);
	}
	



	@Override
	public List<OurCare> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getServicesByCareType(String careType) {
		// TODO Auto-generated method stub
		return ourCareRepository.findDistinctCare(careType);
	}

	
	  @Override public OurCare getServices(String careType) 
	  { // TODOAuto-generated method stub 
	  return null; 
	  }

	@Override
	public void addCombo(MultipartFile file, Combo combo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OurCare getServiceByID(String careID) {
		return ourCareRepository.findByCareId(careID);
	}
}
