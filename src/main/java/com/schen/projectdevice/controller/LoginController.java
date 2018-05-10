package com.schen.projectdevice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.schen.projectdevice.model.FileEntity;
import com.schen.projectdevice.model.UserInfo;
import com.schen.projectdevice.service.FileService;

@Controller
public class LoginController {
	@Autowired
	FileService fileService;
	
	
	@GetMapping("/access")
	public String showPage(Model model) {
		model.addAttribute("user", new UserInfo());
		System.out.println("Open log in window");
		return "bootstrap-login";
	}
	
	@PostMapping("/login")
	public String submitForm( @ModelAttribute("user") UserInfo user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/access?error";
		}
		System.out.println("Processing form");
		List<FileEntity> files = fileService.getFiles();
		model.addAttribute("files", files);
		return "redirect:/main";
	}
	
	@GetMapping("/logout")
	public String toLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/access?logout";
	}
	
	
}
