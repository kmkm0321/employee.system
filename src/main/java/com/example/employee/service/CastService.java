package com.example.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee.entity.WorkRecode;

@Service
public class CastService {
	
	@SuppressWarnings("unchecked")
	public List<WorkRecode> castOtoL(Object obj) {
		List<WorkRecode> castObj = (List<WorkRecode>) obj;
	    return castObj;
	}

}
