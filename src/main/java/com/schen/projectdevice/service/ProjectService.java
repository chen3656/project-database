package com.schen.projectdevice.service;

import java.util.List;

import com.schen.projectdevice.model.Project;

public interface ProjectService {
	
	public List<Project> getAllProjects();

	public Project getProjectById(int id);

	public Project getProjectByName(String name);

	public void saveProject(Project project);

	public void deleteProjectById(int id);

	public List<Project> searchProjectByKeyword(String key);
}
