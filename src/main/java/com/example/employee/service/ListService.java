package com.example.employee.service;

import java.util.List;




import org.springframework.stereotype.Service;


import com.example.employee.entity.EmployeeList;
import com.example.employee.form.ListForm;
import com.example.employee.repository.AttendanceListRepository;
import com.example.employee.repository.EmployeeListRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListService{
	EmployeeListRepository employeeListRepository;
	AttendanceListRepository attendanceListRepository;
	
	public List<EmployeeList> branching(ListForm listform){
		String branch = listform.getBranch();
		String department = listform.getDepartment();
		List<EmployeeList> result;
		
		if(branch.isEmpty() == true && department.isEmpty() == false) {
			result = employeeListRepository.findByDepertment(department);
		}else if(branch.isEmpty() == false && department.isEmpty() == true) {
			result = employeeListRepository.findByBranch(branch);
		}else if(branch.isEmpty() == true && department.isEmpty() == true) {
			result = employeeListRepository.findAll();
		}else{
			result = employeeListRepository.findByBranchAndDepertment(branch,department);
		}
		return result;
	}
	
}