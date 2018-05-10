package com.schen.projectdevice.service;

import java.util.List;

import com.schen.projectdevice.model.DataSheet;

public interface DataSheetService {
	public DataSheet getDataSheetById(int id);

	public void saveDataSheet(DataSheet dataSheet);

	public void deleteDataSheetById(int id);

	public List<DataSheet> getAllDataSheet();
	
	public List<DataSheet> getDataSheetByKeyword(String key);
	
	public List<DataSheet> getDataSheetByProductFamily(String productFamily);
}
