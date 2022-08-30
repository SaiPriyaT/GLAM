package com.glam.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.LatestNews;
import com.glam.repository.LatestNewsRepository;
@Service
public class LatestNewsServiceImpl implements LatestNewsService {
@Autowired
private LatestNewsRepository newsRepository;

	

	@Override
	public LatestNews getListByID(int newsID){
		// TODO Auto-generated method stub
		return newsRepository.getById(newsID);
	}

	@Override
	public LatestNews addLatestNews(LatestNews LatestNews) {
		// TODO Auto-generated method stub
		return newsRepository.save(LatestNews);
	}

	@Override
	public void updateLatestNews(LatestNews latestnews) {
		// TODO Auto-generated method stub
		this.newsRepository.save(latestnews);
	}


	@Override
	public void deleteNewsById(int newsID) {
		// TODO Auto-generated method stub
		this.newsRepository.deleteById(newsID);
	}

	@Override
	public LatestNews GetNewsById(int id) {
		// TODO Auto-generated method stub
		return newsRepository.getById(id);
	}

	@Override
	public void saveLatestNews(LatestNews LatestNews) {
		// TODO Auto-generated method stub
		this.newsRepository.save( LatestNews);
	}

	@Override
	public List<LatestNews> listAllNews() {
		// TODO Auto-generated method stub
		return  newsRepository.findAll();
	}

	@Override
	public void addLatestNews(MultipartFile file, LatestNews latestnews) {
		// TODO Auto-generated method stub
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
		System.out.println("not a a valid file");
		}
		try {
			latestnews.setData(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
		e.printStackTrace();
		}
		newsRepository.save(latestnews);
		}
	}





