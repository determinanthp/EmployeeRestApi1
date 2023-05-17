package com.example.springbootbackend.service.impl;

import com.example.springbootbackend.EmployeeRepository;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getALlEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
      /*  Optional<Employee> employee= employeeRepository.findById(id);
        if (employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee", "id",id);
        }*/
        return employeeRepository.findById(id).orElseThrow(()->
                              new ResourceNotFoundException("Employee","id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        // check whether the employee with given id is existing in DB or not
        Employee employee1= employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
        Employee employee2= employeeRepository.getReferenceById(id);
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());
        // save employee1 to DB
        employeeRepository.save(employee1);
        return employee1;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.deleteById(id);
    }
}
