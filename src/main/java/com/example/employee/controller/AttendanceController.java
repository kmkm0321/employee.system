package com.example.employee.controller;

import java.text.ParseException;


import org.springframework.security.core.annotation.AuthenticationPrincipal;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.employee.entity.AttendanceList;
import com.example.employee.entity.LoginUserDetail;
import com.example.employee.service.AttendanceService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AttendanceController {
	
	private AttendanceService attendanceService;
	
	@GetMapping("/attendance")
	public ModelAndView getAttendance(@AuthenticationPrincipal LoginUserDetail loginUserDetail,
			ModelAndView mv) throws ParseException {
		AttendanceList todaysRecode = attendanceService.getTodaysRecode(loginUserDetail.getId());
		mv.addObject("todaysRecode",todaysRecode);
		mv.addObject("detail",loginUserDetail);
		mv.setViewName("attendance");
		return mv;
	}
	
	@PostMapping("/attendance")
	public ModelAndView postAttendance(@RequestParam("attendance") int value,
			@AuthenticationPrincipal LoginUserDetail loginUserDetail,
			ModelAndView mv) throws ParseException{
		try {
			int execution = attendanceService.addStatus(value,loginUserDetail.getId());
			mv.addObject("execution", execution);
		}catch(Exception e) {
			int execution = 50;
			mv.addObject("execution", execution);
		}
		AttendanceList todaysRecode = attendanceService.getTodaysRecode(loginUserDetail.getId());
		mv.addObject("todaysRecode",todaysRecode);
		mv.setViewName("attendance");
		return mv;
	}
	
}
