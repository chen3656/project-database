package com.schen.projectdevice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.schen.projectdevice.model.UserInfo;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public UserInfo getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		boolean enable = true;
		Query<UserInfo> query = session.createQuery("from UserInfo where username=:name and enabled=:enable", UserInfo.class);
		query.setParameter("name", username);
		query.setParameter("enable", enable);
		List<UserInfo> users = query.getResultList();
		UserInfo user = null;
		if (users == null || users.isEmpty()) {
			return user;
		}
		user = users.get(0);
		return user;
	}

	@Override
	@Transactional
	public void saveUser(UserInfo user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

}
