package com.schen.projectdevice.dao;

import java.util.List;

import com.schen.projectdevice.model.DataSheet;

public interface DataSheetDao {
	public DataSheet getDataSheetById(int id);
	
	public void saveDataSheet(DataSheet dataSheet);
	
	public void deleteDataSheetById(int id);
	
	public List<DataSheet> getAllDataSheet();
	
	public List<DataSheet> getDataSheetByKeyword(String key);
	
	public List<DataSheet> getDataSheetByProductFamily(String productFamily);
}
