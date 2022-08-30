package com.glam.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.Combo;
import com.glam.beans.OurCare;

public interface OurCareService {
	
//Page<OurCare> listAllServices(int pageNumber,String sortField,String sortDir,String keyword);

List<OurCare> listAllServices();

OurCare getServiceByID(int careID);

OurCare addService(OurCare ourCare);

void updateService(OurCare ourCare);

List<OurCare> findByCareType(String careType);

OurCare getServiceByID(String careID);


void deleteServiceById(int careID);

public List<OurCare> getAllServicesByStoreID(int storeid,int branchid);

void addService(MultipartFile file, OurCare ourCare);

List<OurCare> findByKeyword(String keyword);

OurCare getServices(String careType);

List<String> getServicesByCareType(String careType);

void addCombo(MultipartFile file, Combo combo);

}
