package com.schen.projectdevice.model;

import org.springframework.web.multipart.MultipartFile;

public class DSWrapper {
	
	private String productFamily;
	private String deviceName;
	private MultipartFile file;

	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
