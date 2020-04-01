package com.fc.psi.security.service;

import com.fc.psi.security.config.SecurityPasswordEncoder;
import com.fc.psi.security.config.SecurityYml;
import com.fc.psi.security.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private SecurityYml securityYml;
    @Autowired
    private SecurityPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        if(StringUtils.isEmpty(username) && !securityYml.getUsername().equals(username)){
            throw new UsernameNotFoundException("用户不存在！");
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_AMDIN"));
        String password = passwordEncoder.encode(securityYml.getPassword());
        return new SecurityUser(securityYml.getUsername(),password,authorities);
    }
}
