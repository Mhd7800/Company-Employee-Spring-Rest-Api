package com.example.EnocaChallenge.EmployeeService.EmployeeServiceImpl;

import com.example.EnocaChallenge.EmployeeService.EmployeeService;
import com.example.EnocaChallenge.Entity.Employee;
import com.example.EnocaChallenge.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();

    }

    @Override
    public void DeleteEmployee(Integer id) {
        Employee existingEmployee=employeeRepository.findById(id).get();
        if(null!=existingEmployee)
        {
                employeeRepository.deleteById(id);
        }
    }

    @Override
    public Employee SaveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee UpdateEmployee(Employee employee, Integer id) {
        Employee existingEmployee = employeeRepository.findById(id).get();
        if (null != existingEmployee) {
            existingEmployee.setId(employee.getId());
            existingEmployee.setCompany(employee.getCompany());
            existingEmployee.setName(employee.getName());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setWorking_year(employee.getWorking_year());
        }
        employeeRepository.save(existingEmployee);
        return existingEmployee;

    }



    public double findSalary(Employee employee) {


        double salary = 0.0;
        if (employee.getAge()>=20 && employee.getAge()<=25)
        {
            salary = employee.getSalary() + employee.getSalary()*0.1 +employee.getSalary()*employee.getWorking_year()*0.1;
            return salary;
        }
        if (employee.getAge()>=26 && employee.getAge()<=30)
        {
            salary = employee.getSalary() + employee.getSalary()*0.08 +employee.getSalary()*employee.getWorking_year()*0.1;
            return salary;
        }
        if (employee.getAge()>=31 && employee.getAge()<=36)
        {
            salary = employee.getSalary() + employee.getSalary()*0.05 +employee.getSalary()*employee.getWorking_year()*0.1;
            return salary;
        }
        if (employee.getAge()>=36)
        {
            salary = employee.getSalary() + employee.getSalary()*0.03 +employee.getSalary()*employee.getWorking_year()*0.1;;
            return salary;
        }
        else
        {
            return employee.getSalary();
        }


    }

    @Override
    public List<Employee> findByAgeAndWorkingYear(Integer age, Integer workingYear) {
        List<Employee> employees = employeeRepository.findByAgeAndWorkingYear(age, workingYear);
        employees.stream().forEach( employee -> {
                employee.setSalary(findSalary(employee));
                employeeRepository.save(employee);
                });
       return employeeRepository.saveAll(employees);
    }
}


