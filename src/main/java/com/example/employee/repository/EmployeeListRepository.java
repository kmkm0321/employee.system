package com.example.employee.repository;

import java.util.List;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.entity.EmployeeList;

@Transactional
@Repository
public interface EmployeeListRepository extends JpaRepository<EmployeeList,Integer>{
	
	@Query(value = "SELECT * FROM employee_list "
			+ "WHERE branch = ?1 AND department = ?2 ORDER BY branch, department, name ASC", nativeQuery = true)
	public List<EmployeeList> findByBranchAndDepertment(String branch,String department);
	
	@Query(value = "SELECT * FROM employee_list "
			+ "WHERE department = ?1 ORDER BY branch, department, name ASC", nativeQuery = true)
	public List<EmployeeList> findByDepertment(String department);
	
	@Query(value = "SELECT * FROM employee_list "
			+ "WHERE branch = ?1 ORDER BY branch, department, name ASC", nativeQuery = true)
	public List<EmployeeList> findByBranch(String branch);
	
	@Query(value = "SELECT * FROM employee_list "
			+ "ORDER BY branch, department, name ASC", nativeQuery = true)
	public List<EmployeeList> findAll();
	
	@Query(value = "SELECT * FROM employee_list WHERE employee_id = ?1", nativeQuery = true)
	public List<EmployeeList> findByStatus(Integer id);
	
	@Modifying
	@Query(value = "UPDATE employee_list SET status = ?1 WHERE employee_id = ?2", nativeQuery = true)
	void statusUpdate(String status,Integer id);
	
	@Modifying
	@Query(value = "UPDATE employee_list SET status = ?1 WHERE email = ?2", nativeQuery = true)
	void statusUpdate(String status,String email);
	
	@Modifying
	@Query(value = "UPDATE employee_list SET remarks = ?1 WHERE employee_id = ?2", nativeQuery = true)
	void test(String remarks,Integer id);

}
