package com.kotuko.usermanagementsystem.config;

import com.kotuko.usermanagementsystem.user.dal.IUserDAL;
import com.kotuko.usermanagementsystem.user.entity.Role;
import com.kotuko.usermanagementsystem.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserDAL iUserDAL;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = iUserDAL.findByUsernameIgnoreCase(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist!!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.get().getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new CustomUserDetails(user.get().getEmail(), user.get().getPassword(), true, authorities);
    }
}
