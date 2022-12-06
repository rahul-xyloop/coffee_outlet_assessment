package com.digital.coffee_outletassessment.security;

import com.digital.coffee_outletassessment.entities.UserRole;
import com.digital.coffee_outletassessment.entities.Users;
import com.digital.coffee_outletassessment.repository.RoleRepo;
import com.digital.coffee_outletassessment.repository.UserRoleRepo;
import com.digital.coffee_outletassessment.repository.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This check the credential of user
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private RoleRepo roleRepo;

    /**
     * @param username as String  which identify the user whose data is required.
     * @return UserDetails return user with credentials and roles
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername method called");
        log.debug("username is "+ username);
        Optional<Users> optional = usersRepo.findUserByUserName(username);
        /*
         It checks that if user with given username not found it throwing the exception
         */
        if (optional.isEmpty()) {
            log.info("user not found"+username);
            throw new RuntimeException("user Not found " + username);

        }
        // one user have many role so it's retrieving the user role list on the basis of user id
        List<UserRole> userRoleList = userRoleRepo.getUserRole(optional.get().getId());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(roleRepo.getRoleNameById(userRole.getRoleId())));
        }

        return new User(optional.get().getUsername(), securityConfig
                .bCryptPasswordEncoder()
                .encode(optional.get().getPassword()), grantedAuthorityList);
    }
}
