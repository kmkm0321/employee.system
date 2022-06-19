package com.example.employee.entity;

import javax.persistence.Column;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.employee.form.SignUpForm;

import lombok.Data;

@Entity
@Table(name="employee_list")
@Data
public class EmployeeList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="how_to_read")
	private String read;
	
	@Column(name="birthday")
	private String birthday;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="branch")
	private String b_id;
	
	@Column(name="department")
	private String d_id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass")
	private String password;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="roll")
	private String roll;
	
	public EmployeeList(SignUpForm signUpForm) {		
		this.name = signUpForm.getName();
		this.read = signUpForm.getRead();
		this.birthday = signUpForm.getBirthday();
		this.gender = signUpForm.getGender();
		this.b_id = signUpForm.getB_id();
		this.d_id = signUpForm.getD_id();
		this.tel = signUpForm.getTel();
		this.email = signUpForm.getEmail();
		this.password = signUpForm.getPassword();
		this.remarks = signUpForm.getRemarks();
		this.roll = signUpForm.getRoll();
	}
	
	public EmployeeList() {
	}

}
