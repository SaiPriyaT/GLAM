package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.LatestNews;
@Repository 
public interface LatestNewsRepository extends JpaRepository<LatestNews, Integer>{
	@Query(value = "SELECT newsid, news_items, news_description, created,upload_file\r\n"
			+ " FROM public.latest_news\r\n"
			+ " WHERE CURRENT_DATE-INTERVAL '30 DAY'< created;", nativeQuery = true)
			List<LatestNews> findPresentNews();
}
