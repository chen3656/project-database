package com.schen.projectdevice.service;

import java.util.List;

import com.schen.projectdevice.model.Version;

public interface VersionService {
	
	public List<Version> getAllVersions();

	public Version getVersionById(int id);

	public void saveVersion(Version version);

	public void deleteVersionById(int id);
	
	public List<Version> getVersionsByProjectId(int id);
}
