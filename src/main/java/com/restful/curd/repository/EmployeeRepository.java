package com.restful.curd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.curd.model.Employee;

@Repository
	public interface EmployeeRepository 
	        extends JpaRepository<Employee, Long> {
	 
	
}
