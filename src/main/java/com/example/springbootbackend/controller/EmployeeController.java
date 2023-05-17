package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // Build create employee REST
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    //Build all GET employees REST API
    @GetMapping
    public List<Employee>getAllEmployees(){
        return employeeService.getALlEmployees();
    }

    //Build GET employee by id REST API
   // http:// localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeid){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeid), HttpStatus.OK);

    }
    //update Employee REST API
    //http:// localhost: 8080/ api/ employee 1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //Build delete REST API
    //http:// localhost: 8080/api/employee 1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        //Delete employee from database
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.OK);
    }
}
