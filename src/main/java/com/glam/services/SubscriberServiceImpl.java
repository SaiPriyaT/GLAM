package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Subscriber;
import com.glam.repository.SubscriberRepository;
@Service
public class SubscriberServiceImpl implements SubscriberService{
	
	@Autowired
	private SubscriberRepository subscriberRepository;

	@Override
	public List<Subscriber> getAllsubscribers() {
		// TODO Auto-generated method stub
		return subscriberRepository.findAll();
	}

	@Override
	public void updatesubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		subscriberRepository.save(subscriber);
	}

	@Override
	public Subscriber getsubscriberById(int subscriberid) {
		// TODO Auto-generated method stub
		return subscriberRepository.findById(subscriberid).get();
	}

	@Override
	public void deletesubscriberById(int subscriberid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Subscriber savesubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		return subscriberRepository.save(subscriber);
	}


	

}
