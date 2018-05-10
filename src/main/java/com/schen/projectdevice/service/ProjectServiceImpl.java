package com.schen.projectdevice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schen.projectdevice.dao.ProjectDao;
import com.schen.projectdevice.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Override
	@Transactional 
	public List<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}

	@Override
	@Transactional
	public Project getProjectById(int id) {
		return projectDao.getProjectById(id);
	}

	@Override
	@Transactional
	public Project getProjectByName(String name) {
		return projectDao.getProjectByName(name);
	}

	@Override
	@Transactional
	public void saveProject(Project project) {
		projectDao.saveProject(project);
	}

	@Override
	@Transactional
	public void deleteProjectById(int id) {
		projectDao.deleteProjectById(id);
	}

	@Override
	@Transactional
	public List<Project> searchProjectByKeyword(String key) {
		return projectDao.searchProjectByKeyword(key);
	}

}
