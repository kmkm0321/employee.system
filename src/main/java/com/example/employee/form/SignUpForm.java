package com.example.employee.form;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank
	private String name;
	@NotBlank
	private String read;
	@NotBlank
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String birthday;//Date型だとエラーが出る
	@NotBlank
	private String gender;
	@NotBlank
	private String b_id;
	@NotBlank
	private String d_id;
	@NotNull
	@Pattern(regexp = "\\d{2,4}\\d{2,4}\\d{4}$")
	private String tel;//Integer型だとエラーが出る
	@NotBlank
	@Email
	private String email;
	@Length(min=3)
	private String password;
	
	private String remarks;
	@NotNull
	private String roll;
}
