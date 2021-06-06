package com.restful.curd.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restful.curd.model.Employee;
import com.restful.curd.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl {
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * @return	List<Employee>
	 * {@summary}	list of all employees
	 */
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

	/**public Employee createOrUpdateEmployee(Employee entity) {
		Optional<Employee> employee = employeeRepository.findById(entity.getId());

		if (employee.isPresent()) {
			Employee newEntity = employee.get();
			newEntity.setEmployeeName(entity.getEmployeeName());
			newEntity.setEmployeeDesignation(entity.getEmployeeDesignation());
			newEntity.setSalary(entity.getSalary());

			newEntity = employeeRepository.save(newEntity);

			return newEntity;
		} else {
			entity = employeeRepository.save(entity);

			return entity;
		}
	}*/
	
	/**
	 * @return	void
	 * {@summary}	save employees
	 */
	@Transactional
	public void saveEmployee(Employee entity) {
		employeeRepository.save(entity);
	}
	/**
	 * @return	Employee
	 * {@summary}	get employee by id
	 */
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).get();
	}
	/**
	 * @return	void
	 * {@summary}	delete employee
	 */
	@Transactional
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
}
