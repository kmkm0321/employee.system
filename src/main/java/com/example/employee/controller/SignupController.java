package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.employee.entity.EmployeeList;
import com.example.employee.form.SignUpForm;
import com.example.employee.repository.EmployeeListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes(types=SignUpForm.class)
public class SignupController {
	@Autowired
	EmployeeListRepository employeeListRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@ModelAttribute("signUpForm")
	public SignUpForm setUpForm(){
		return new SignUpForm();
	}
	
	@GetMapping("/signup")
	public ModelAndView signUp(@ModelAttribute SignUpForm signUpForm, ModelAndView mv) {
		mv.addObject("signUpForm", new SignUpForm());
		mv.setViewName("signup");
		return mv;
	}
	
	@PostMapping("/signup/check")
	public ModelAndView signUpCheck(@ModelAttribute @Validated SignUpForm signUpForm, BindingResult bindingResult, ModelAndView mv) {
		System.out.println(bindingResult.hasErrors());//エラー有無
		log.info(signUpForm.toString());//ログ
		if(bindingResult.hasErrors()) {
			mv.addObject("signUpForm", signUpForm);
			mv.setViewName("/signup");
			return mv;
		}
		mv.addObject("signUpForm", signUpForm);
		mv.setViewName("/signup/check");
		return mv;
	}
	
	@GetMapping("/signup/complete")
	public ModelAndView signUpComplete(@ModelAttribute SignUpForm signUpForm, ModelAndView mv) {
		EmployeeList employeeList = new EmployeeList(signUpForm);
		employeeList.setPassword(passwordEncoder.encode(employeeList.getPassword()));
		try{
			employeeListRepository.saveAndFlush(employeeList);
			employeeListRepository.statusUpdate("f", signUpForm.getEmail());
		}catch(Exception e) {
			mv.addObject("judgement", false);
			mv.setViewName("/signup/complete");
			return mv;
		}
		mv.addObject("judgement", true);
		mv.setViewName("/signup/complete");
		return mv;
	}
	
}
