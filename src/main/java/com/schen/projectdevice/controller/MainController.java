package com.schen.projectdevice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.schen.projectdevice.model.DataSheet;
import com.schen.projectdevice.model.Project;
import com.schen.projectdevice.service.DataSheetService;
import com.schen.projectdevice.service.ProjectService;


@Controller
public class MainController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired 
	private DataSheetService dataSheetService;
	
	@GetMapping("/add") 
	public String addProject(ModelMap model) {
		Project project = new Project();
		model.addAttribute("project", project);
		return "add-project";
	}
	
	@PostMapping("/addProject") 
	public String addProject(@ModelAttribute("project") Project project) throws IOException {
		projectService.saveProject(project);
		return "redirect:/main";
	}
	
	@GetMapping("/edit")
	public String editProject(@RequestParam("projectId") int id, Model model) {
		Project project = projectService.getProjectById(id);
		model.addAttribute("project", project);
		return "add-project";
	} 
	
	
	@GetMapping("/deleteProject")
	public String deleteProject(@RequestParam("projectId") int id) {
		projectService.deleteProjectById(id);
		return "redirect:/main";
	}
	
	@RequestMapping(value="/datasheet/{productFamily}", method=RequestMethod.GET)
	public String getDataSheet(ModelMap model, @PathVariable String productFamily) {
		List<DataSheet> dataSheets = null;
		if (productFamily.equals("all")) {
			dataSheets = dataSheetService.getAllDataSheet();
		} else {
			System.out.println("Prod: " + productFamily);
			dataSheets = dataSheetService.getDataSheetByProductFamily(productFamily);
		}
		model.addAttribute("dataSheets", dataSheets);
		return "data-sheet";
	}
}
