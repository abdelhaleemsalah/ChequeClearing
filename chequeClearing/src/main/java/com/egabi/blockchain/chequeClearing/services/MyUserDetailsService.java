package com.egabi.blockchain.chequeClearing.services;

import com.egabi.blockchain.chequeClearing.entities.PortalUser;
import com.egabi.blockchain.chequeClearing.entities.UserRole;
import com.egabi.blockchain.chequeClearing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        PortalUser portalUser = userRepository.findPortalUserByUsername(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(portalUser.getUserRole());

        return buildUserForAuthentication(portalUser, authorities);

    }

    private User buildUserForAuthentication(PortalUser user,
                                                  List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<>(setAuths);

        return Result;
    }
}
