package com.egabi.blockchain.chequeClearing.repositories;

import com.egabi.blockchain.chequeClearing.entities.PortalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<PortalUser, Long> {

    PortalUser findPortalUserByUsername(String username);
}
