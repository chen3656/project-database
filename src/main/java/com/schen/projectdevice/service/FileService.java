package com.schen.projectdevice.service;

import java.util.List;

import com.schen.projectdevice.model.FileEntity;

public interface FileService {
	// Get all Project Files in the database
	List<FileEntity> getFiles();

	// Save a new device to the database
	void saveFile(FileEntity file);

	// Find the files with primary ID
	FileEntity getFileById(int id);

	// Delete the selected device in database
	void deleteFileById(int id);

	// Get the files for selected version
	List<FileEntity> getFilesByVerId(int id);

	// Search file with specific key word
	List<FileEntity> searchFileByKeyword(String name);
}
