package com.schen.projectdevice.service;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;

import com.schen.projectdevice.dto.UserDto;
import com.schen.projectdevice.model.UserInfo;

public interface UserService {
	//@Secured ({"ROLE_ADMIN", "ROLE_USER"})
	UserInfo getUserByUserName(String userName);

	void saveUser(@Valid UserDto userDto);

	void saveAdmin(@Valid UserDto userDto);

}
