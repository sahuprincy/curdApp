package com.restful.curd.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restful.curd.model.Employee;
import com.restful.curd.service.EmployeeServiceImpl;
import com.restful.curd.view.ResponseData;

@RestController
public class RestfulCurdController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	/**
	 * @return <List<Employee>>
	 * {@summary}- Display all the employee Data
	 */
	@GetMapping("/all-employees")
	private ResponseEntity<List<Employee>> fetchData() {
		return new ResponseEntity<>(employeeServiceImpl.getAllEmployees(), HttpStatus.OK);
	}

	/**@PostMapping("/create-update-employee")
	public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
		Employee updated = employeeServiceImpl.createOrUpdateEmployee(employee);
		return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}*/
	
	/**
	 * @return <ResponseData>
	 * {@summary}- Save the employee Data
	 */
	@PostMapping("/all-employees")
    public ResponseEntity<ResponseData> add(@RequestBody Employee emp) {
		employeeServiceImpl.saveEmployee(emp);
		ResponseData responseData= new ResponseData();
    	responseData.setStatus(true);
    	responseData.setMessage("Data Saved SuccessFully ");
    	return new ResponseEntity<ResponseData>(responseData,HttpStatus.CREATED);
    }
	/**
	 * @return <Object>
	 * {@summary}- fetch the employee Data by id
	 */
	@GetMapping("/employee-by-id/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
		try {
			Employee emp = employeeServiceImpl.getEmployee(id);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			ResponseData responseData = new ResponseData();
			responseData.setStatus(false);
			responseData.setMessage("id: " + id + "not Found");

			return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
		}
	}
	/**
	 * @return <T>
	 * {@summary}- update the  employee Data by id
	 */
	@PutMapping("/update-employee/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		try {
			 employeeServiceImpl.getEmployee(id);
			employee.setId(id);
			employeeServiceImpl.saveEmployee(employee);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * @return <ResponseData>
	 * {@summary} delete the employee Data by id
	 */
	@DeleteMapping("/delete-by-id/{id}")
	public ResponseEntity<ResponseData> deleteEmployee(@PathVariable Long id) {

		employeeServiceImpl.deleteEmployee(id);
		ResponseData responseData = new ResponseData();
		responseData.setStatus(false);
		responseData.setMessage("id: " + id + " Deleted Successfully");
		return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);

	}

}
