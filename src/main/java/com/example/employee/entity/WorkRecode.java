package com.example.employee.entity;

import java.sql.Time;

import lombok.Data;

@Data
public class WorkRecode {
	
	public WorkRecode(String date, String day_of_week, 
			String attendance, Time s_time, Time e_time, Time w_time) {
		this.date = date;
		this.day_of_week = day_of_week;
		this.attendance = attendance; 
		this.s_time = s_time;
		this.e_time = e_time;
		this.w_time = w_time;
	}
	
	private String date;
	private String day_of_week;
	private String attendance;
	private Time s_time;
	private Time e_time;
	private Time w_time;

}
