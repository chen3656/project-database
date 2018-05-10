package com.schen.projectdevice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schen.projectdevice.dao.VersionDao;
import com.schen.projectdevice.model.Version;

@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	private VersionDao versionDao;
	
	@Override
	@Transactional
	public List<Version> getAllVersions() {
		return versionDao.getAllVersions();
	}

	@Override
	@Transactional
	public Version getVersionById(int id) {
		return versionDao.getVersionById(id);
	}

	@Override
	@Transactional
	public void saveVersion(Version version) {
		versionDao.saveVersion(version);

	}

	@Override
	@Transactional
	public void deleteVersionById(int id) {
		versionDao.deleteVersionById(id);
	}

	@Override
	@Transactional
	public List<Version> getVersionsByProjectId(int id) {
		return versionDao.getVersionsByProjectId(id);
	}

}
