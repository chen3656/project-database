package com.schen.projectdevice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.schen.projectdevice.model.FileEntity;

@Repository
public class FileDaoImpl implements FileDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FileEntity> getFiles() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<FileEntity>query = 
				currentSession.createQuery("from FileEntity order by id", FileEntity.class);
		List<FileEntity> files = query.getResultList();
		return files;
	}

	@Override
	public void saveFile(FileEntity file) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(file);
	}

	@Override
	public FileEntity getFileById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		FileEntity file = currentSession.get(FileEntity.class, id);
		return file;
	}

	@Override
	public void deleteFileById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from FileEntity where id=:fileId");
		query.setParameter("fileId", id);
		query.executeUpdate();

	}

	@Override
	public List<FileEntity> searchFileByKeyword(String name) {
		Query<FileEntity> query = null;
		Session currentSession = sessionFactory.getCurrentSession();
		List<FileEntity> fileEntitys;
		if (name == null || name.trim().length() == 0) {
			query = 
					currentSession.createQuery("from FileEntity", FileEntity.class);
			fileEntitys = query.getResultList();
			return fileEntitys;
		}
		query =
				currentSession.createQuery(
						"from FileEntity where lower(fileName) like:theName or lower(fileDescrption) like:theName", FileEntity.class);
		query.setParameter("theName", "%" + name.toLowerCase() + "%");
		fileEntitys = query.getResultList();
		return fileEntitys;
	}

	@Override
	public List<FileEntity> getFilesByVerId(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<FileEntity>query = 
				currentSession.createQuery("from FileEntity file where file.version.id=:theId order by file.id", FileEntity.class);
		query.setParameter("theId", id);
		List<FileEntity> files = query.getResultList();
		return files;
	}

}
