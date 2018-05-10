package com.schen.projectdevice.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.schen.projectdevice.model.FileEntity;
import com.schen.projectdevice.model.Project;
import com.schen.projectdevice.model.Version;
import com.schen.projectdevice.service.FileService;
import com.schen.projectdevice.service.ProjectService;
import com.schen.projectdevice.service.VersionService;

@Controller
public class VersionController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired 
	private VersionService versionService;
	
	@Autowired 
	private FileService fileService;
	
	@GetMapping("/version")
	public String showVersions(@RequestParam("projectId") int id, ModelMap model) {
		Project project = projectService.getProjectById(id);
		List<Version> versions = versionService.getVersionsByProjectId(id);
		model.addAttribute("project", project);
		model.addAttribute("versions", versions);
		model.addAttribute("version", new Version());
		return "version-list";
	}
	
	@PostMapping("/addVersion")
	public String addVersion(@ModelAttribute("version") Version version, @RequestParam("projectId") int id, ModelMap model) {
		Project project = projectService.getProjectById(id);
		//project.add(version);	
		version.setProject(project);
		versionService.saveVersion(version);
		//projectService.saveProject(project);
		return "redirect:/version?projectId="+id;
	}
	
	@GetMapping("/file") 
	public String filePage(HttpServletRequest request, @RequestParam("verId") int id, ModelMap model) {
		ServletContext servletContext = request.getSession().getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String folderPath = contextPath + File.separator + "temp";
		try {
			FileUtils.cleanDirectory(new File(folderPath));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Version version = versionService.getVersionById(id);
		model.addAttribute("version", version);
		List<FileEntity> files= fileService.getFilesByVerId(id);
		model.addAttribute("files", files);
		model.addAttribute("projectId", version.getProject().getId());
		return "files";
	}
	
	@GetMapping("/deleteVersion")
	public String deleteVersion(@RequestParam("verId") int ver, @RequestParam("projectId") int id, ModelMap model) {
		versionService.deleteVersionById(ver);
		return "redirect:/version?projectId="+id;
	}
}
