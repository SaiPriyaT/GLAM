package com.glam.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.LatestNews;

public interface LatestNewsService {
	List<LatestNews> listAllNews() ;
	LatestNews getListByID(int id);
	LatestNews addLatestNews(LatestNews LatestNews);
	void saveLatestNews(LatestNews LatestNews);
	void updateLatestNews(LatestNews latestnews);
	LatestNews GetNewsById(int id);
	void deleteNewsById(int newsId);
	void addLatestNews(MultipartFile file,LatestNews latestnews);

}


