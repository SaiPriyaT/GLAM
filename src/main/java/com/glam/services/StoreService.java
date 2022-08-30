package com.glam.services;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.Store;
import com.glam.beans.StoreAdmin;



public interface StoreService {

List<Store> getAllStore();
Store addNewStore(Store store);
void updateStore(Store store);
Store getStoreById(int store_id);
StoreAdmin addAdmin(StoreAdmin storeAdmin);
StoreAdmin updateAdmin(StoreAdmin storeAdmin);
StoreAdmin getAdmin(String adminEmail, String adminName);
void addStorewithImageVideos(MultipartFile Imagefile,MultipartFile Videofile,Store store);

void deleteStoreById(int storeid);

Store saveStore(Store store);

StoreAdmin getAdmin(int adminID);
StoreAdmin findbyEmail(String adminEmail);
public String getAdminEmailId(String adminEmail);

public String sendSimpleEmail(String toEmail,
String body,
String subject);
}