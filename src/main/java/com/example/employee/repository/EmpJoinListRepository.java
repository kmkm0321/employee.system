package com.example.employee.repository;

import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.EmpJoinList;

@Repository
public interface EmpJoinListRepository extends JpaRepository<EmpJoinList,Integer>{
	
	@Query(value = "SELECT e.employee_id as id,e.name as name,e.how_to_read as how_to_read, "
			+ "b.b_name as b_name,d.d_name as d_name,e.status as status,e.tel as tel, "
			+ "e.email as email,e.pass as pass,e.remarks as remarks,e.roll as roll "
			+ "FROM employee_list e INNER JOIN branch_list b ON e.branch = b.b_id "
			+ "INNER JOIN department_list d ON e.department = d.d_id  WHERE email = ?1", nativeQuery = true)
	public Optional<EmpJoinList> findByEmail(String email);
	/*
	@Query(value = "SELECT e.id as id,e.name as name,e.how_to_read as how_to_read, "
			+ "b.b_name as b_name,d.d_name as d_name,e.status as status,e.tel as tel, "
			+ "e.email as email,e.pass as pass,e.remarks as remarks,e.roll as roll "
			+ "FROM employee_list e INNER JOIN branch_list b ON e.branch = b.b_id "
			+ "INNER JOIN department_list d ON e.department = d.d_id "
			+ "WHERE name LIKE %?1% OR how_to_read LIKE %?1% OR b_name LIKE %?1% OR d_name LIKE %?1%", nativeQuery = true)
	public List<EmpJoinList> find(String text);*/
	
	@Query(value = "SELECT e.employee_id as id,e.name as name,e.how_to_read as how_to_read, "
			+ "b.b_name as b_name,d.d_name as d_name,e.status as status,e.tel as tel, "
			+ "e.email as email,e.pass as pass,e.remarks as remarks,e.roll as roll "
			+ "FROM employee_list e INNER JOIN branch_list b ON e.branch = b.b_id "
			+ "INNER JOIN department_list d ON e.department = d.d_id WHERE employee_id = ?1", nativeQuery = true)
	public EmpJoinList findUser(Integer id);
	
	@Query(value = "SELECT e.employee_id as id,e.name as name,e.how_to_read as how_to_read, "
			+ "b.b_name as b_name,d.d_name as d_name,e.status as status,e.tel as tel, "
			+ "e.email as email,e.pass as pass,e.remarks as remarks,e.roll as roll "
			+ "FROM employee_list e INNER JOIN branch_list b ON e.branch = b.b_id "
			+ "INNER JOIN department_list d ON e.department = d.d_id "
			+ "WHERE (name LIKE %?1% OR how_to_read LIKE %?1% OR b_name LIKE %?1% OR d_name LIKE %?1%) "
			+ "AND roll = 'ROLL_USER' "
			+ "ORDER BY branch, department, name", nativeQuery = true)
	public Page<EmpJoinList> find(String text, Pageable pageable);
	
}
