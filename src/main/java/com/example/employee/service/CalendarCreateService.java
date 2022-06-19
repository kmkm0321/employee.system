package com.example.employee.service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class CalendarCreateService {
		
	public String[] calendarCreate(int year,int month) {
		
		String week_name[] = {"","(日)","(月)","(火)","(水)","(木)","(金)","(土)"};
		String dayofweek = "";
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String[] date = new String[days];
		
		for(int i = 1; i <= days; i++){
			cal.set(Calendar.DATE, i);
			dayofweek = week_name[cal.get(Calendar.DAY_OF_WEEK)];
			date[i-1] = month + "/" + i + dayofweek;
		}
		return date;
	}
	
	public int getDays(int year, int month) {//指定した年月の日数を取得するメソッド必要か検討中
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}
	
	public Date getCarrentDate() throws ParseException {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formatD = new SimpleDateFormat("yyyy-MM-dd");	
		Date date = java.sql.Date.valueOf(formatD.format(cal.getTime()));
		return date;
	}
	
	public Time getCarrentTime() throws ParseException {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formatT = new SimpleDateFormat("HH:mm:ss");			
		Time time = java.sql.Time.valueOf(formatT.format(cal.getTime()));		
		return time;
	}

}
