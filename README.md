request for save employee: {

"name": "varma",
"dob": "2000-06-14T13:07:21Z",
"address": {
	
	"line1": "Madhapur",
	"line2": "",
	"city": "Hyd",
	"state": "Telangana",
	"country": "India",
	"zipCode": "500083"
}
} EmployeeApplication.java: package com.example.employee;

import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication public class EmployeeApplication {

public static void main(String[] args) {
	SpringApplication.run(EmployeeApplication.class, args);
}
}

EmployeeController.java : package com.example.employee.controller; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.GetMapping; import org.springframework.web.bind.annotation.PathVariable; import org.springframework.web.bind.annotation.PostMapping; import org.springframework.web.bind.annotation.RequestBody; import org.springframework.web.bind.annotation.RequestMapping; import org.springframework.web.bind.annotation.ResponseStatus; import org.springframework.web.bind.annotation.RestController; import com.example.employee.request.EmployeeDto; import com.example.employee.service.EmployeeResource;

@RestController @RequestMapping(value = "/v1/bfs") public class EmployeeController {

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

EmployeeResource.java package com.example.employee.service;

import org.springframework.stereotype.Service; import com.example.employee.request.EmployeeDto;

@Service public interface EmployeeResource {

EmployeeDto fetchEmployeeById(int id);
EmployeeDto save(EmployeeDto employeeDto);
}

EmployeeResourceImplimentation.java package com.example.employee.service;

import java.util.Optional; import org.modelmapper.ModelMapper; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Component; import com.example.employee.models.Employee; import com.example.employee.repositories.EmployeeRepository; import com.example.employee.request.EmployeeDto;

@Component public class EmployeeResourceImplementation implements EmployeeResource {

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

EmployeeRepository.java package com.example.employee.service;

import org.springframework.stereotype.Service; import com.example.employee.request.EmployeeDto;

@Service public interface EmployeeResource {

EmployeeDto fetchEmployeeById(int id);
EmployeeDto save(EmployeeDto employeeDto);
}

model objects Address.java: package com.example.employee.models;

import javax.persistence.Entity; import javax.persistence.GeneratedValue; import javax.persistence.GenerationType; import javax.persistence.Id; import javax.persistence.Table;

@Entity @Table(name = "address") public class Address {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String line1;
private String line2;
private String city;
private String state;
private String country;
private String zipCode;

public String getLine1() {
	return line1;
}

public void setLine1(String line1) {
	this.line1 = line1;
}

public String getLine2() {
	return line2;
}

public void setLine2(String line2) {
	this.line2 = line2;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}
}

Employee.java: package com.example.employee.models;

import java.util.Date; import javax.persistence.CascadeType; import javax.persistence.Entity; import javax.persistence.GeneratedValue; import javax.persistence.GenerationType; import javax.persistence.Id; import javax.persistence.JoinColumn; import javax.persistence.OneToOne; import javax.persistence.Table;

@Entity @Table(name = "employee") public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private Date DOB;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "addressid")
private Address address = new Address();

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public Date getDOB() {
	return DOB;
}

public void setDOB(Date dOB) {
	DOB = dOB;
}
}

EmployeeDto.java: package com.example.employee.request;

import java.util.Date;

public class EmployeeDto {

private int id;
private String name;
private Date DOB;
private AddressDto address;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public AddressDto getAddress() {
	return address;
}

public void setAddress(AddressDto address) {
	this.address = address;
}

public Date getDOB() {
	return DOB;
}

public void setDOB(Date dOB) {
	DOB = dOB;
}
} AddressDto.java: package com.example.employee.request;

public class AddressDto { private int id; private String line1; private String line2; private String city; private String state; private String country; private String zipCode;

public String getLine1() {
	return line1;
}

public void setLine1(String line1) {
	this.line1 = line1;
}

public String getLine2() {
	return line2;
}

public void setLine2(String line2) {
	this.line2 = line2;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
}

Junits for controller: package com.example.employee.controller;

import static org.hamcrest.CoreMatchers.is; import static org.mockito.Mockito.when; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.http.MediaType; import org.springframework.test.context.ContextConfiguration; import org.springframework.test.context.TestPropertySource; import org.springframework.test.web.servlet.MockMvc; import com.example.employee.EmployeeApplication; import com.example.employee.request.EmployeeDto; import com.example.employee.service.EmployeeResource; import com.example.employee.utils.EmployeeUtils; import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class) @ContextConfiguration(classes = { EmployeeApplication.class }) @TestPropertySource(locations = "classpath:application.properties") class EmployeeControllerTest { @MockBean private EmployeeResource empResource;

@Autowired
private MockMvc mvc;

@Autowired
private ObjectMapper objMapper;

private EmployeeDto employeeDto;

@BeforeEach
void setUp() throws Exception {
	employeeDto = EmployeeUtils.employeDtoMock();
}

private String baseUri = "http://localhost:8080/v1/bfs/";
private String empUri = baseUri + "employees/{id}";
private String empSaveUri = baseUri + "employee";

@Test
void testFetchEmployeeById() throws Exception {
	int id = 2;
	when(empResource.fetchEmployeeById(2)).thenReturn(employeeDto);
	mvc.perform(get(empUri, id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(employeeDto.getId())));
}

@Test
void testSave() throws Exception {
	when(empResource.save(employeeDto)).thenReturn(employeeDto);
	mvc.perform(post(empSaveUri).contentType(MediaType.APPLICATION_JSON)
			.content(objMapper.writeValueAsString(employeeDto))).andExpect(status().isCreated());
			
}
}

EmployeeUtils: package com.example.employee.utils;

import com.example.employee.request.AddressDto; import com.example.employee.request.EmployeeDto;

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