package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glam.beans.SocialNetworks;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetworks,Integer> {

}
