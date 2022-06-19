package com.example.employee.controller;

import java.io.BufferedInputStream;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee.entity.EmpJoinList;
import com.example.employee.entity.LoginUserDetail;
import com.example.employee.entity.WorkRecode;
import com.example.employee.repository.AttendanceListRepository;
import com.example.employee.repository.EmpJoinListRepository;
import com.example.employee.service.AttendanceService;
import com.example.employee.service.CalendarCreateService;
import com.example.employee.service.CastService;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@AllArgsConstructor
@Controller
@SessionAttributes("result")
public class ServiceRecodeController {
	
	EmpJoinListRepository empJoinListRepository;
	AttendanceListRepository attendanceListRepository;
	CalendarCreateService calendarCreateService;
	AttendanceService attendanceService;
	HttpSession session;
	CastService castService;
	
	@GetMapping("/service/recode")
	public ModelAndView getServiceRecode(ModelAndView mv, 
			@PageableDefault(page = 0, size = 5) Pageable pageable,
			@ModelAttribute("formText")String formText,
			@RequestParam(name = "access", defaultValue = "")String access) {
		if(access.equals("first")) {
			session.removeAttribute("sessionText");
		}
		String sessionText = (String) session.getAttribute("sessionText");
		if(formText.isEmpty()) {
			if(sessionText != null) {
				Page<EmpJoinList> result = empJoinListRepository.find(sessionText, pageable);
				mv.addObject("page", result);
				mv.setViewName("/service/recode");
			}else {
				mv.addObject("page", null);
				mv.setViewName("/service/recode");
			}
		}else {
			Page<EmpJoinList> result = empJoinListRepository.find(formText, pageable);
			mv.addObject("page", result);
			mv.setViewName("/service/recode");
		}
		return mv;
	}
	
	@PostMapping("/service/recode")
	public String postServiceRecode(@RequestParam("text") String formText,
			@PageableDefault(page = 0, size = 5) Pageable pageable, 
			RedirectAttributes redirectAttributes) {
		if(formText.isEmpty()) {
			redirectAttributes.addFlashAttribute("formText", formText);
			session.removeAttribute("sessionText");
		}else{
			//List<EmpJoinList> result = empJoinListRepository.find(text);
			redirectAttributes.addFlashAttribute("formText", formText);
			session.setAttribute("sessionText", formText);
		}
		return "redirect:/service/recode";
	}
	
	@GetMapping("/service/recode-user")
	public ModelAndView getUser(@RequestParam("id") Integer id, ModelAndView mv,
			@AuthenticationPrincipal LoginUserDetail userDetails) {
		if(id == 0) {
			id = userDetails.getId();
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int[] selected = {year, month};
		String[] date = calendarCreateService.calendarCreate(year, month);
		List<WorkRecode> listWorkRecode = attendanceService.workRecodeCreate(id, year, month, date);
		session.setAttribute("listWorkRecode", listWorkRecode);
		EmpJoinList user = empJoinListRepository.findUser(id);
		mv.addObject("selected", selected);
		mv.addObject("listWorkRecode", listWorkRecode);
		mv.addObject("user", user);
		mv.setViewName("/service/recode-user");
		return mv;
	}
	
	@PostMapping("/service/recode-user")
	public ModelAndView postUser(@RequestParam("year") Integer year,
			@RequestParam("month") Integer month,
			@RequestParam("id") Integer id, ModelAndView mv) {
		int[] selected = {year, month};
		String[] date = calendarCreateService.calendarCreate(year, month);
		List<WorkRecode> listWorkRecode = attendanceService.workRecodeCreate(id, year, month, date);
		session.setAttribute("listWorkRecode", listWorkRecode);
		EmpJoinList user = empJoinListRepository.findUser(id);
		mv.addObject("selected", selected);
		mv.addObject("listWorkRecode", listWorkRecode);
		mv.addObject("user", user);
		mv.setViewName("/service/recode-user");
		return mv;
	} 
	
	@GetMapping("/service/recode-user/output")
	
	public void output(HttpServletResponse res) throws JRException, IOException {
		//jasperファイルの格納先を取得して文字列へ
		File path = new File("C:\\sts-4.13.1.RELEASE\\work_space\\EmployeeSystem4\\src\\main\\resources\\pdf\\Blank_A4.jasper");
		String jasperFilePath = path.getPath();
		//fillreportの引数に（ファイルのパス、パラメータ、データソース）を設定
		/*SampleEntity te01 = new SampleEntity("date", "none", "09:30", "18:00", "08:00");
		SampleEntity te02 = new SampleEntity("date", "none", "09:30", "18:00", "08:00");
		SampleEntity te03 = new SampleEntity("date", "none", "09:30", "18:00", "08:00");*/
		//List<SampleEntity> list= new ArrayList<SampleEntity>();
		Object obj = session.getAttribute("listWorkRecode");
		List<WorkRecode> list = castService.castOtoL(obj);
		/*list.add(te01);
		list.add(te02);
		list.add(te03);*/
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
		//JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();データソースの値が空の場合はこっち
		
		//fillreportの引数に（ファイルのパス、パラメータ、データソース）を設定
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		JasperPrint jasperPrint= JasperFillManager.fillReport(jasperFilePath, parameterMap, jrBeanCollectionDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "jasper.pdf");
		
		//出力されたpdfのパスを設定HTTPレスポンスのコンテンツタイプ、ヘッダー情報を設定
		File outputPath = new File("C:\\sts-4.13.1.RELEASE\\work_space\\EmployeeSystem4\\jasper.pdf");
		res.setContentType("application/pdf");
		res.setHeader("Content-Disposition", "inline");
		//
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(outputPath));
	    BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
		
	    int x;
	    while((x=in.read()) != -1){
	        out.write(x);
	    }
	    in.close();
	    out.close();
		
	}

}
