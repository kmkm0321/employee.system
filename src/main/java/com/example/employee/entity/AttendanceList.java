package com.example.employee.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AttendanceList {
	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Integer id;
	
	@Id
	@Column(name="date")
	private Date date;
	
	@Column(name="s_time")
	private Time s_time;
	
	@Column(name="e_time")
	private Time e_time;
	
	@Column(name="work_time")
	private Time work_time;
	
}
