package com.example.employee.repository;


import java.sql.Date;


import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.entity.AttendanceList;

@Transactional
@Repository
public interface AttendanceListRepository extends JpaRepository<AttendanceList,Date>{
	@Modifying
	@Query(value = "INSERT INTO attendance_list VALUES(?1, ?2, ?3)", nativeQuery = true)
	void s_timeUpdate(Integer id,Date date,Time time);
	
	@Modifying
	@Query(value = "UPDATE attendance_list SET e_time = ?1 WHERE employee_id = ?2 "
			+ "AND date = (SELECT MAX(date) FROM attendance_list)", nativeQuery = true)
	void e_timeUpdate(Time time,Integer id);
	
	@Query(value = "SELECT employee_id, date, s_time, e_time, e_time - s_time as work_time "
			+ "FROM attendance_list WHERE employee_id = ?1 "
			+ "ORDER BY date ASC", nativeQuery = true)
	public List<AttendanceList> getRecode(Integer id);
	
	@Query(value = "SELECT employee_id, date, s_time, e_time, e_time - s_time as work_time"
			+ " FROM attendance_list WHERE employee_id = ?1 AND date = ?2", nativeQuery = true)
	public AttendanceList getTodaysRecode(Integer id, Date todaysDate);
}
