package com.schen.projectdevice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schen.projectdevice.dto.UserDto;
import com.schen.projectdevice.model.UserInfo;
import com.schen.projectdevice.service.UserService;

@Controller
public class RegistrationController {
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(ModelMap model) {
		UserDto userDto = new UserDto();
		model.addAttribute("admin", "");
		model.addAttribute("userDto", userDto);
		return "register";
	}

	@PostMapping("/register")
	public String registerUserAccount(@Valid @ModelAttribute("userDto")  UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/register?error";
		}
		UserInfo user = userService.getUserByUserName(userDto.getUsername());
		if (user != null) {
			result.rejectValue("username", null, "User already exist");
		}
		if (result.hasErrors()) {
			return "redirect:/register?error";
		}
		userService.saveUser(userDto);
		return "redirect:/register?success";
	}
	@GetMapping("/register/admin") 
	public String registerForAdmin(ModelMap model){
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		model.addAttribute("admin", "/admin");
		return "register";
	}
	
	@PostMapping("/register/admin")
	public String registerAdminAccount(@Valid @ModelAttribute("userDto")  UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/register?error";
		}
		UserInfo user = userService.getUserByUserName(userDto.getUsername());
		if (user != null) {
			result.rejectValue("username", null, "User already exist");
		}
		if (result.hasErrors()) {
			return "redirect:/register?error";
		}
		userService.saveAdmin(userDto);
		return "redirect:/register?success";
	}
	
	@RequestMapping("/logout")
	public String logoutPage() {
		return "redirect: /login";
	}
}
