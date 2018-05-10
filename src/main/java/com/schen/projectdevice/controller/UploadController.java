package com.schen.projectdevice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.schen.projectdevice.model.DSWrapper;
import com.schen.projectdevice.model.DataSheet;
import com.schen.projectdevice.model.DocumentFile;
import com.schen.projectdevice.model.FileEntity;
import com.schen.projectdevice.model.UserInfo;
import com.schen.projectdevice.model.Version;
import com.schen.projectdevice.service.DataSheetService;
import com.schen.projectdevice.service.FileService;
import com.schen.projectdevice.service.UserService;
import com.schen.projectdevice.service.VersionService;

@Controller
public class UploadController {
	@Autowired
	private DataSheetService dataSheetService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private VersionService versionService;
	
	
	@GetMapping("/import")
	public String uploadForm(Authentication authentication, @RequestParam("verId") int id, ModelMap model) {
		authentication.getPrincipal();
		UserInfo user = userService.getUserByUserName(authentication.getName());
		if (user == null) {
			return "bootstrap-login";
		}
		Version version = versionService.getVersionById(id);
		model.addAttribute("version", version);
		model.addAttribute("document", new DocumentFile());
		return "upload-form";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadFile(@RequestParam("verId") int id,
			 @ModelAttribute("documentFile") DocumentFile documentFile, Model model) throws IOException {
		Version version = versionService.getVersionById(id);
		FileEntity file = new FileEntity(documentFile);
		file.setVersion(version);
		//project.add(files);
		fileService.saveFile(file);
		return "redirect:/file?verId=" + id;
	}
	
	@GetMapping("/importDS")
	public String uploadDSForm(Authentication authentication, ModelMap model) {
		authentication.getPrincipal();
		UserInfo user = userService.getUserByUserName(authentication.getName());
		if (user == null) {
			return "bootstrap-login";
		}
		model.addAttribute("dataSheet", new DSWrapper());
		return "upload-DS";
	}
	
	@RequestMapping(value="/uploadDS", method=RequestMethod.POST)
	public String uploadFile(@ModelAttribute("dataSheet") DSWrapper dsWrapper, Model model) throws IOException {
		DataSheet dataSheet = new DataSheet();
		dataSheet.setProductFamily(dsWrapper.getProductFamily());
		dataSheet.setDeviceName(dsWrapper.getDeviceName());
		MultipartFile file = dsWrapper.getFile();
		dataSheet.setFileName(file.getOriginalFilename());
		dataSheet.setFileType(file.getContentType());
		dataSheet.setFileContent(file.getBytes());
		dataSheetService.saveDataSheet(dataSheet);
		return "redirect:/datasheet/all";
	}
}
