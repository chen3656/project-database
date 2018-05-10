package com.schen.projectdevice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schen.projectdevice.dao.DataSheetDao;
import com.schen.projectdevice.model.DataSheet;
@Service
public class DataSheetServiceImpl implements DataSheetService {
	
	@Autowired 
	private DataSheetDao dataSheetDao;
	
	@Override
	@Transactional
	public DataSheet getDataSheetById(int id) {
		return dataSheetDao.getDataSheetById(id);
	}

	@Override
	@Transactional
	public void saveDataSheet(DataSheet dataSheet) {
		dataSheetDao.saveDataSheet(dataSheet);
	}

	@Override
	@Transactional
	public void deleteDataSheetById(int id) {
		dataSheetDao.deleteDataSheetById(id);

	}

	@Override
	@Transactional
	public List<DataSheet> getAllDataSheet() {
		return dataSheetDao.getAllDataSheet();
	}

	@Override
	@Transactional
	public List<DataSheet> getDataSheetByKeyword(String key) {
		return dataSheetDao.getDataSheetByKeyword(key);
	}

	@Override
	@Transactional
	public List<DataSheet> getDataSheetByProductFamily(String productFamily) {
		return dataSheetDao.getDataSheetByProductFamily(productFamily);
	}

}
