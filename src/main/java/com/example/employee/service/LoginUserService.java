package com.example.employee.service;

import java.util.Optional;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee.entity.EmpJoinList;
import com.example.employee.entity.LoginUserDetail;
import com.example.employee.repository.EmpJoinListRepository;

@Service
public class LoginUserService implements UserDetailsService {
	
	@Autowired
	EmpJoinListRepository empJoinListRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		Optional<EmpJoinList> empJoinList = empJoinListRepository.findByEmail(username);
		return empJoinList.map(LoginUserDetail::new).get();
	}

}
