package com.schen.projectdevice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schen.projectdevice.dao.FileDao;
import com.schen.projectdevice.model.FileEntity;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileDao fileDao;
	
	@Override
	@Transactional
	public List<FileEntity> getFiles() {
		return fileDao.getFiles();
	}

	@Override
	@Transactional
	public void saveFile(FileEntity file) {
		fileDao.saveFile(file);
		
	}

	@Override
	@Transactional
	public FileEntity getFileById(int id) {
		return fileDao.getFileById(id);
	}

	@Override
	@Transactional
	public void deleteFileById(int id) {
		fileDao.deleteFileById(id);
	}

	@Override
	@Transactional
	public List<FileEntity> searchFileByKeyword(String name) {
		return fileDao.searchFileByKeyword(name);
	}

	@Override
	@Transactional
	public List<FileEntity> getFilesByVerId(int id) {
		return fileDao.getFilesByVerId(id);
	}

}
