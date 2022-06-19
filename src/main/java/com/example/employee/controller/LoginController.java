package com.example.employee.controller;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public ModelAndView getLogin(ModelAndView mv, @ModelAttribute("errorFlag")String flag) {
		mv.addObject("errorFlag", flag);
		mv.setViewName("/login");
		return mv;
	}
	
	@PostMapping("/login")
	public String postLogin() {
		return "redirect:/list";
	}
	
	@GetMapping("/login_error")
	public String loginError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorFlag", "error");
		return "redirect:/login";
	}
			
}
