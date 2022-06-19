package com.example.employee.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmpJoinList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="how_to_read")
	private String read;
	
	@Column(name="b_name")
	private String b_name;
	
	@Column(name="d_name")
	private String d_name;
	
	@Column(name="status")
	private String status;
	
	@Column(name="tel")
	private Integer tel;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass")
	private String password;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="roll")
	private String roll;

}
