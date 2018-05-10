package com.schen.projectdevice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.schen.projectdevice.model.DataSheet;
import com.schen.projectdevice.model.Project;
import com.schen.projectdevice.service.DataSheetService;
import com.schen.projectdevice.service.ProjectService;
import com.schen.projectdevice.service.UserService;

@Controller
public class DefaultController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	UserService userService;
	
	@Autowired
	DataSheetService dataSheetService;
	
	//Redirect to the home page
	@GetMapping("/home")
	public String toHome() {
		return "welcome";
	}
	
	// Keyword searching function
	@GetMapping("/searchProject")
	public String searchProject(@RequestParam("keyword") String keyword, ModelMap model, Authentication authentication) {
		authentication.getPrincipal();
		model.addAttribute("user", userService.getUserByUserName(authentication.getName()));
		System.out.println("keyword: " + keyword);
		List<Project> projects = projectService.searchProjectByKeyword(keyword);
		model.addAttribute("projects", projects);
		return "project-list";
	}
    
	@GetMapping("/searchDS")
	public String searchPataSheet(@RequestParam("key") String keyword, ModelMap model) {
		System.out.println("keyword: " + keyword);
		List<DataSheet> dataSheets = dataSheetService.getDataSheetByKeyword(keyword);
		model.addAttribute("dataSheets", dataSheets);
		return "data-sheet";
	}
	// Project List Page
	@GetMapping("/main")
	public String toMain(ModelMap model, Authentication authentication) {
		authentication.getPrincipal();
		model.addAttribute("user", userService.getUserByUserName(authentication.getName()));
		System.out.println("welcome: " + authentication.getName());
		List<Project> projects = projectService.getAllProjects();
		model.addAttribute("projects", projects);
		return "project-list";
	}
	
	@GetMapping("/search")
	public String searchAll(@RequestParam("keyword") String keyword, ModelMap model) {
		List<Project> projects = projectService.searchProjectByKeyword(keyword);
		List<DataSheet> dataSheets = dataSheetService.getDataSheetByKeyword(keyword);
		model.addAttribute("projects", projects);
		model.addAttribute("dataSheets", dataSheets);
		return "full-list";
	}
	
	@GetMapping("/DSHome")
	public String toDSHome() {
		return "DS-home";
	}
	
	@GetMapping("/projectHome")
	public String toProjectHome() {
		return "project-home";
	}
	
	// Permission Denied page
	@GetMapping(value = "/error")
	public String error() {
		return "403";
	}
	
	// Redirect to manual page
	@GetMapping("/manual")
	public String toManual() {
		return "manual";
	}
}
