package com.example.EnocaChallenge.EmployeeService;

import com.example.EnocaChallenge.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> getAllEmployee();
    Employee getEmployeeById(Integer id);
    void DeleteEmployee(Integer id);
    Employee SaveEmployee(Employee employee);
    Employee UpdateEmployee (Employee employee, Integer id);
    List<Employee> findByAgeAndWorkingYear(Integer age, Integer workingYear);
}
