package com.example.employee.utils;

import com.example.employee.request.AddressDto;
import com.example.employee.request.EmployeeDto;

public class EmployeeUtils {

	public static EmployeeDto employeDtoMock() {
		EmployeeDto employeeDto = new EmployeeDto();
		AddressDto addressDto = new AddressDto();
		employeeDto.setId(2);
		employeeDto.setName("Varma");
		addressDto.setCity("Hyd");
		addressDto.setCountry("India");
		addressDto.setState("Telangana");
		employeeDto.setAddress(addressDto);
		return employeeDto;
	}
	
	

}
