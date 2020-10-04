package com.example.employee.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.employee.models.Employee;
import com.example.employee.repositories.EmployeeRepository;
import com.example.employee.request.EmployeeDto;

@Component
public class EmployeeResourceImplementation implements EmployeeResource {

	@Autowired
	private EmployeeRepository empRepo;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public EmployeeDto fetchEmployeeById(int id) {
		EmployeeDto employeeDto = null;
		Optional<Employee> optEmp = empRepo.findById(id);
		if (optEmp.isPresent())
			employeeDto = modelMapper.map(optEmp.get(), EmployeeDto.class);
		return employeeDto;
	}

	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		employee = empRepo.save(employee);
		return modelMapper.map(employee, EmployeeDto.class);
	}

}
