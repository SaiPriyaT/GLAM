package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.Subscriber;
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {


}
