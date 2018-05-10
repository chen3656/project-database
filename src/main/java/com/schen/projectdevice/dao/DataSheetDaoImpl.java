package com.schen.projectdevice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.schen.projectdevice.model.DataSheet;
@Repository
public class DataSheetDaoImpl implements DataSheetDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public DataSheet getDataSheetById(int id) {
		Session session = sessionFactory.getCurrentSession();
		DataSheet dataSheet = session.get(DataSheet.class, id);
		return dataSheet;
	}

	@Override
	public void saveDataSheet(DataSheet dataSheet) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(dataSheet);
	}

	@Override
	public void deleteDataSheetById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("delete from DataSheet where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<DataSheet> getAllDataSheet() {
		Session session = sessionFactory.getCurrentSession();
		Query<DataSheet> query = session.createQuery("from DataSheet order by productFamily", DataSheet.class);
		List<DataSheet> dataSheets = query.getResultList();
		return dataSheets;
	}

	@Override
	public List<DataSheet> getDataSheetByKeyword(String key) {
		Session session = sessionFactory.getCurrentSession();
		Query<DataSheet> query = null;
		if (key == null || key.length() == 0) {
			query = session.createQuery("from DataSheet order by productFamily", DataSheet.class);
		} else {
			query = 
				session.createQuery("from DataSheet where "
						+ "lower(deviceName) like:key "
						+ "or lower(fileName) like:key "
						+ "or lower(productFamily) like:key "
						+ "order by productFamily", DataSheet.class);
			query.setParameter("key", "%" + key.toLowerCase() + "%");
		}
		List<DataSheet> dataSheets = query.getResultList();
		return dataSheets;
	}

	@Override
	public List<DataSheet> getDataSheetByProductFamily(String productFamily) {
		Session session = sessionFactory.getCurrentSession();
		Query<DataSheet> query = session.createQuery("from DataSheet where productFamily =:str order by productFamily", DataSheet.class);
		query.setParameter("str", productFamily);
		List<DataSheet> dataSheets = query.getResultList();
		return dataSheets;
	}

}
