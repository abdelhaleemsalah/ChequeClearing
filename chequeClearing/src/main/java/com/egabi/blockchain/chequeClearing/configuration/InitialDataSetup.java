package com.egabi.blockchain.chequeClearing.configuration;

import com.egabi.blockchain.chequeClearing.entities.PortalUser;
import com.egabi.blockchain.chequeClearing.entities.UserRole;
import com.egabi.blockchain.chequeClearing.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@Configuration
public class InitialDataSetup {

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {

        return (args) -> {

            PortalUser user = new PortalUser();
            UserRole userRole = new UserRole();
            userRole.setPortalUser(user);
            userRole.setRole("ROLE_USER");

            HashSet<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setUsername("superuser");
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            user.setEnabled(true);
            user.setUserRole(userRoles);




            userRepository.save(user);

            System.out.println(userRepository.findAll());
            System.out.println("Haleeeeem");
        };
    }

    public void superuser(){

    }
}
