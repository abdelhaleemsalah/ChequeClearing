package com.egabi.blockchain.chequeClearing.services;

import com.egabi.blockchain.chequeClearing.entities.PortalUser;

public interface UserProcessingService 
{
	public void saveUserDetails(PortalUser newUser);
}
