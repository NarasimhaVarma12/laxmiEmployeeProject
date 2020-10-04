package com.example.employee.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.employee.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
