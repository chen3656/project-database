package com.schen.projectdevice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.schen.projectdevice.model.Version;

@Repository
public class VersionDaoImpl implements VersionDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Version> getAllVersions() {
		Session session = sessionFactory.getCurrentSession();
		Query<Version> query = 
				session.createQuery("from Version order by id", Version.class);
		List<Version> versions= query.getResultList();
		return versions;
	}

	@Override
	public Version getVersionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Version version = session.get(Version.class, id);
		return version;
	}

	@Override
	public void saveVersion(Version version) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(version);
	}

	@Override
	public void deleteVersionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = 
				session.createQuery("delete from Version where id=:theId");
		query.setParameter("theId", id);
		query.executeUpdate();
	}

	@Override
	public List<Version> getVersionsByProjectId(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Version> query = 
				session.createQuery("from Version version where version.project.id=:theId order by version.id", Version.class);
		query.setParameter("theId", id);
		List<Version> versions= query.getResultList();
		return versions;
	}

}
