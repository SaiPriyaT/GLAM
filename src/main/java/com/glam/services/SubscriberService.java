package com.glam.services;

import java.util.List;

import com.glam.beans.CustomerVisit;
import com.glam.beans.Store;
import com.glam.beans.Subscriber;


public interface SubscriberService {
	List<Subscriber> getAllsubscribers();
	
	void updatesubscriber(Subscriber subscriber);

	Subscriber getsubscriberById(int subscriberid);

	void deletesubscriberById(int subscriberid);

	Subscriber savesubscriber(Subscriber subscriber);
	
//	Long findbyMobileNumber(Long moboleNumber);

}
