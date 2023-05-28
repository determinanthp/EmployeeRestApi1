package com.example.springbootbackend;

import com.example.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
