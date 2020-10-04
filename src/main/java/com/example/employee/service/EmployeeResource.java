package com.example.employee.service;

import org.springframework.stereotype.Service;
import com.example.employee.request.EmployeeDto;

@Service	
public interface EmployeeResource {

	EmployeeDto fetchEmployeeById(int id);
	EmployeeDto save(EmployeeDto employeeDto);
	
}
