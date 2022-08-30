
package com.glam.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.Store;
import com.glam.beans.StoreAdmin;
import com.glam.repository.AdminRepository;
import com.glam.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storerepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public List<Store> getAllStore() {
// TODO Auto-generated method stub
		return storerepository.findAll();
	}

	@Override
	public Store addNewStore(Store store) {
// TODO Auto-generated method stub
		return storerepository.save(store);
	}

	@Override
	public void updateStore(Store store) {
// TODO Auto-generated method stub
		storerepository.save(store);
	}

	@Override
	public Store getStoreById(int store_id) {
// TODO Auto-generated method stub
		return storerepository.getById(store_id);
	}

	@Override
	public StoreAdmin addAdmin(StoreAdmin storeAdmin) {
// TODO Auto-generated method stub
		return adminRepository.save(storeAdmin);
	}

	@Override
	public StoreAdmin getAdmin(String adminEmail, String adminPassword) {
// TODO Auto-generated method stub
		return adminRepository.findByEmailIgnoreCaseAndPassword(adminEmail, adminPassword);
	}

	@Override
	public void deleteStoreById(int storeid) {
// TODO Auto-generated method stub
	}

	@Override
	public Store saveStore(Store store) {
// TODO Auto-generated method stub
		return storerepository.save(store);
	}

	public StoreAdmin getAdmin(int adminID) {
// TODO Auto-generated method stub
		return adminRepository.findById(adminID).get();
	}

	@Override
	public String getAdminEmailId(String adminEmail) {
// TODO Auto-generated method stub
		return adminRepository.findByStoreAdminEmailIdIgnoreCase(adminEmail);
	}

	@Override
	public StoreAdmin updateAdmin(StoreAdmin storeAdmin) {
// TODO Auto-generated method stub
		return adminRepository.save(storeAdmin);
	}

	@Override
	public void addStorewithImageVideos(MultipartFile Imagefile, MultipartFile Videofile, Store store) {
		String fileName = StringUtils.cleanPath(Imagefile.getOriginalFilename());

		if (fileName.contains("")) {
			System.out.println("Not a Valid File");
		}
		try {
			store.setStoreImage(Base64.getEncoder().encodeToString(Imagefile.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		storerepository.save(store);

		String fileName1 = StringUtils.cleanPath(Videofile.getOriginalFilename());

		if (fileName1.contains("")) {
			System.out.println("Not a Valid File");
		}
		try {
			store.setStoreVideo(Base64.getEncoder().encodeToString(Videofile.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		storerepository.save(store);
	}

	@Override
	public String sendSimpleEmail(String toEmail, String body, String subject) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("glamservices5@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		javaMailSender.send(message);
		System.out.println("Mail Sent...");
		return "successfulRegistration";
	}

	@Override
	public StoreAdmin findbyEmail(String adminEmail) {
		// TODO Auto-generated method stub
		return adminRepository.findByEmail(adminEmail);
	}
}
