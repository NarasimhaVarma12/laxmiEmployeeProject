package com.example.employee.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.employee.request.EmployeeDto;
import com.example.employee.service.EmployeeResource;

@RestController
@RequestMapping(value = "/v1/bfs")
public class EmployeeController {
	
	@Autowired
	private EmployeeResource empResource;
	
	@GetMapping(value = "/employees/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeDto fetchEmployeeById(@PathVariable("id") int id) {
		return empResource.fetchEmployeeById(id);
		
	}

	@PostMapping(value = "/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
		
		return empResource.save(employeeDto);
	}
}
