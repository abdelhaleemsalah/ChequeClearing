package com.egabi.blockchain.chequeClearing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egabi.blockchain.chequeClearing.entities.PortalUser;
import com.egabi.blockchain.chequeClearing.repositories.UserRepository;

@Repository
public class UserProcessingServiceImpl implements UserProcessingService
{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void saveUserDetails(PortalUser newUser) {
		// TODO Auto-generated method stub
		userRepo.save(newUser);
		
	}

}
