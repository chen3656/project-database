package com.schen.projectdevice.dao;

import com.schen.projectdevice.model.UserInfo;

public interface UserDao {
	public UserInfo getUserByUsername(String username);

	public void saveUser(UserInfo user);
}
