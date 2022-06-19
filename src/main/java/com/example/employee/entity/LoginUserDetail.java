package com.example.employee.entity;

import java.util.Arrays;





import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.employee.repository.EmpJoinListRepository;
import lombok.Data;

@Data
public class LoginUserDetail implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private Integer id;
	private String name;
	private String read;
	private String b_name;
	private String d_name;
	private String status;
	private Integer tel;
	private String email;
	private String password;
	private String remarks;
	private String roll;
	//private List<GrantedAuthority> authorities;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmpJoinListRepository empJoinListRepository;
	
	//コンストラクタ
	public LoginUserDetail(EmpJoinList empJoinList) {
		this.id = empJoinList.getId();
		this.email = empJoinList.getEmail();
		this.password = empJoinList.getPassword();
		this.status = empJoinList.getStatus();
		this.roll = empJoinList.getRoll();
		this.name = empJoinList.getName();
		this.read = empJoinList.getRead();
		this.b_name = empJoinList.getB_name();
		this.d_name = empJoinList.getD_name();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return Arrays.asList(new SimpleGrantedAuthority(roll));
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return password;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
