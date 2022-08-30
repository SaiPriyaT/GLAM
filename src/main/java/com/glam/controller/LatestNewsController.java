
  package com.glam.controller;
  
  import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import
  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.glam.beans.LatestNews;
import com.glam.beans.Store;
import com.glam.services.LatestNewsService;
import com.glam.services.StoreService;
@Controller
 
  public class LatestNewsController  {
  
  @Autowired LatestNewsService lastestnewsService;
  @Autowired StoreService storeService;
   
  @GetMapping("/news/{NewsId}")
  public String viewHomePages(Model model, @PathVariable(value="NewsId") int newsID) {
  model.addAttribute("date",LocalDate.now());
  LatestNews obj=lastestnewsService.getListByID(newsID);

  List<Store> storeList = storeService.getAllStore();
  model.addAttribute("objStore", storeList);

  model.addAttribute("list",obj);
  return "newsPage";
  }
@GetMapping("/newsdetails")
public String displayNews(Model model ,LatestNews latestNews ) {
	/*
	 * String crtdDateString=latestNews.getCreatedDate().toString(); String
	 * currentDate=LocalDate.now().toString(); LocalDate dateBefore =
	 * LocalDate.parse(crtdDateString);
	 * 
	 * LocalDate dateAfter = LocalDate.parse(currentDate); long noOfDaysBetween =
	 * ChronoUnit.DAYS.between(dateBefore, dateAfter); if(noOfDaysBetween>=30) {
	 * lastestnewsService.deleteNewsById(latestNews.getNewsID());
	 * 
	 * }
	 */
	 model.addAttribute("news", lastestnewsService.listAllNews());
return "newsList";
}
//} 
@GetMapping("/newstable")
public String viewalldepartment(Model model) {
model.addAttribute("newslist",lastestnewsService.listAllNews());
return "newsList";
}
 
@GetMapping("/deletenews/{NewsId}")
public String deleteNews(Model model, @PathVariable(value="NewsId") int newsID) {
System.out.println("Hi delete");
lastestnewsService.deleteNewsById(newsID);
System.out.println("delete operation is invoked"); 
model.addAttribute("news", lastestnewsService.listAllNews());
model.addAttribute("msg","Deleted Successfully");
return "newsList";
}
@GetMapping("/addnews")
public String showNewNewsForm(Model model) {
	LatestNews news =new LatestNews();
model.addAttribute("objnews",news);

return "addNews";
}
@PostMapping(value="/savenews")
public String addNews(Model model, @RequestParam("file") MultipartFile file,@ModelAttribute(value="objnews")LatestNews objnews) {
	objnews.setIsActive('Y');
System.out.println(objnews.getNewsID()+"   name "+objnews.getNewsDescription());

	  if (objnews.getNewsID()==0) {
		  objnews.setUpdatedDate(LocalDateTime.now());
		  objnews.setCreatedDate(LocalDateTime.now());

	  System.out.println("new record:set id: add :"+objnews.getNewsID());
	  lastestnewsService.addLatestNews(file,objnews);
	  }
	  else {
		  objnews.setUpdatedDate(LocalDateTime.now());
		  System.out.println(" record updated:set id: update :"+objnews.getNewsID());
		  lastestnewsService.addLatestNews(file,objnews);
	  }

	  model.addAttribute("news", lastestnewsService.listAllNews());
	  model.addAttribute("msg","Saved Successfully");

	  return "newsList";
}
@GetMapping("/editnews/{newsID}") 
public String editNews(Model model,@PathVariable(value="newsID")int newsID) {
LatestNews objnews=lastestnewsService.GetNewsById(newsID);
model.addAttribute("objnews",objnews);

System.out.println(objnews.getNewsID());
System.out.println(newsID);
return "addNews";
}

}


  