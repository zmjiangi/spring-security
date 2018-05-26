package com.gzyijian.springsecurity.service;

import com.gzyijian.springsecurity.model.User;
import com.gzyijian.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by zmjiangi on 2018-5-11.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository dao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("未查询到用户" + username + "信息");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

}
