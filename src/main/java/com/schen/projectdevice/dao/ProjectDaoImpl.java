package com.schen.projectdevice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.schen.projectdevice.model.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public List<Project> getAllProjects() {
		Session session = sessionFactory.getCurrentSession();
		Query<Project> query = 
				session.createQuery("from Project order by id", Project.class);
		List<Project> projects = query.getResultList();
		return projects;
	}

	@Override
	public Project getProjectById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Project project = 
				session.get(Project.class, id);
		return project;
	}

	@Override
	public Project getProjectByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query<Project> query = 
				session.createQuery("from Project where name=:theName", Project.class);
		query.setParameter("theName", name);
		List<Project> projects = query.getResultList();
		Project project = new Project();
		if (projects == null || projects.isEmpty()) {
			return project;
		}
		project = projects.get(0);
		return project;
	}

	@Override
	public void saveProject(Project project) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(project);
	}

	@Override
	public void deleteProjectById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = 
				session.createQuery("delete from Project where id=:theId");
		query.setParameter("theId", id);
		query.executeUpdate();
	}

	@Override
	public List<Project> searchProjectByKeyword(String key) {
		Session session = sessionFactory.getCurrentSession();
		Query<Project> query = null;
		if (key == null || key.isEmpty()) {
			query = session.createQuery("from Project order by name", Project.class);
		} else {
			query = 
				session.createQuery("from Project project where "
						+ "lower(project.name) like:theName "
						+ "or lower(project.contactName) like:theName "
						+ "or lower(project.deviceName) like:theName "
						+ "or lower(project.contactEmail) like:theName "
						+ "order by project.id", Project.class);
			query.setParameter("theName", "%" + key.toLowerCase() + "%");
		}
		List<Project> projects= query.getResultList();
		return projects;
	}

}
