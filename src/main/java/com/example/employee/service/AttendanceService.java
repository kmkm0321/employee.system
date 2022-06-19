package com.example.employee.service;

import java.sql.Date;





import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.entity.AttendanceList;
import com.example.employee.entity.WorkRecode;
import com.example.employee.repository.AttendanceListRepository;
import com.example.employee.repository.EmployeeListRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttendanceService {
	EmployeeListRepository employeeListRepository;
	AttendanceListRepository attendanceListRepository;
	CalendarCreateService calendarCreateService;
	
	public AttendanceList getTodaysRecode(Integer id) throws ParseException {
		Date todaysDate = calendarCreateService.getCarrentDate();
		AttendanceList todaysRecode = attendanceListRepository.getTodaysRecode(id,todaysDate);
		return todaysRecode;
	}
	
	@Transactional
	public int addStatus(int value, Integer id) throws Exception {
		String result = employeeListRepository.findByStatus(id).get(0).getStatus();
		int execution = 0;
		Date date = calendarCreateService.getCarrentDate();
		Time time = calendarCreateService.getCarrentTime();
		
		if(value == 1) {
			if(result.equals("f")) {
				employeeListRepository.statusUpdate("t",id);
				attendanceListRepository.s_timeUpdate(id,date,time);
				execution = 10;
			}else {
				execution = 20;
			}
		}else if(value == 2) {
			if(result.equals("t")) {
				employeeListRepository.statusUpdate("f",id);
				attendanceListRepository.e_timeUpdate(time,id);
				execution = 30;
			}else {
				execution = 40;
			}
		}
		return execution;
	}

	public List<WorkRecode> workRecodeCreate(Integer id,Integer year, Integer month, String[] date){
		List<AttendanceList> attendanceList = attendanceListRepository.getRecode(id);
		String st_year = year.toString();
		String st_month = month.toString();
		String day_of_week = "";
		List<WorkRecode> listWorkRecode = new ArrayList<>(date.length);
		
		for(int i = 1; i <= date.length; i++) {
			if(date[i-1].contains("土")) {
				day_of_week = "土";
			}else if(date[i-1].contains("日")) {
				day_of_week = "日";
			}else{
				day_of_week = "";
			}
			WorkRecode workRecode = new WorkRecode(date[i-1], day_of_week, 
					"--", null, null, null);
			listWorkRecode.add(workRecode);
		}
		
		for(AttendanceList list : attendanceList) {
			for(int i = 1; i <= date.length; i++) {
				String str = st_year + "-" + st_month + "-" + i;
				Date days = java.sql.Date.valueOf(str);
				if(list.getDate().equals(days)) {
					if(date[i-1].contains("土")) {
						day_of_week = "土";
					}else if(date[i-1].contains("日")) {
						day_of_week = "日";
					}else{
						day_of_week = "";
					}
					WorkRecode workRecode = new WorkRecode(date[i-1], day_of_week, 
							"出勤", list.getS_time(), list.getE_time(), list.getWork_time());
					listWorkRecode.set(i-1, workRecode);
				}
			}
		}
		return listWorkRecode;
	}
	
}
