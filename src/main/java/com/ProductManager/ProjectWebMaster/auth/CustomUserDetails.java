package com.ProductManager.ProjectWebMaster.auth;
/** This class is used to auth user**/
/** Main classes **/
import com.ProductManager.ProjectWebMaster.models.Role;
import com.ProductManager.ProjectWebMaster.models.User;
/** Main interfaces **/
import com.ProductManager.ProjectWebMaster.interfaces.UserInterface;
import com.ProductManager.ProjectWebMaster.interfaces.RoleInterface;

/** Theses Packages are from Spring Framework authorization,
in our use case is for user authentication
**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetails implements UserDetailsService{
    @Autowired
    private UserInterface userInterface;

    @Autowired
    private RoleInterface roleInterface;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /** Return a specific user using their email
     * @param : String email **/

    public User findUserByEmail(String email){
        return userInterface.findByEmail(email);
    }

    /** Save user using Hash map into the Data base
     * @param : User **/

    public void saveUser (User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // Encrypt password
        Role userRole = roleInterface.findByRole("ADMIN");
        user.setEnabled(true);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        /* Save user to DB */
        userInterface.save(user);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){

        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserAuthentication (User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userInterface.findByEmail(email);
        if(user != null){
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserAuthentication(user, authorities);
        }else {
            throw new UsernameNotFoundException("Unknown user");
        }
    }
}
