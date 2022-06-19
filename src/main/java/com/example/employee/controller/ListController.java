package com.example.employee.controller;

import java.util.List;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.employee.entity.EmployeeList;
import com.example.employee.form.ListForm;
import com.example.employee.repository.AttendanceListRepository;
import com.example.employee.service.ListService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ListController {
	ListService listservice;
	AttendanceListRepository attendanceListRepository;
	
	@GetMapping("/list")
	public String index(/*@AuthenticationPrincipal LoginUserDetail userDetails*/) {
		 //System.out.println(userDetails.getUsername());  // ユーザー名を表示
		 //System.out.println(userDetails.getPassword());  // パスワードを表示
	     //System.out.println(userDetails.getAuthorities().toString());  // 権限情報を表示
		return "list";
	}
	
	@PostMapping("/list")
	public ModelAndView list(@ModelAttribute ListForm listform, ModelAndView mv) {
		List<EmployeeList> result = listservice.branching(listform);
		mv.addObject("list", result);
		mv.setViewName("list");
		return mv;
	}
	
	/*使わなくなった勤務履歴画面
	@GetMapping("/list/history")
	public ModelAndView listDetail(@RequestParam("id") Integer id,
			ModelAndView mv) {
		mv.addObject("list", result);
		mv.setViewName("/list/history");
		return mv;
	}*/
}
