package com.schen.projectdevice.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schen.projectdevice.dao.UserDao;
import com.schen.projectdevice.dto.UserDto;
import com.schen.projectdevice.model.UserInfo;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	@Transactional
	public UserInfo getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUsername(userName);

	}
	@Override
	public void saveUser(@Valid UserDto userDto) {
		UserInfo user = new UserInfo();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setRole("ROLE_USER");
		user.setEnable(true);
		userDao.saveUser(user);
	}
	@Override
	public void saveAdmin(@Valid UserDto userDto) {
		UserInfo user = new UserInfo();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setRole("ROLE_ADMIN");
		user.setEnable(true);
		userDao.saveUser(user);
		
	}

}
