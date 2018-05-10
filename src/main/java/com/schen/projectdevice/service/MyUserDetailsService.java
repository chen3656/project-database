package com.schen.projectdevice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.schen.projectdevice.dao.UserDao;
import com.schen.projectdevice.model.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userDao.getUserByUsername(username);
		if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		UserDetails userDetails = 
				(UserDetails)new User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
