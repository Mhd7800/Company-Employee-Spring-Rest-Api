package com.example.EnocaChallenge.EmployeeController;

import com.example.EnocaChallenge.CompanyService.CompanyService;
import com.example.EnocaChallenge.EmployeeService.EmployeeService;
import com.example.EnocaChallenge.Entity.Company;
import com.example.EnocaChallenge.Entity.Employee;
import com.example.EnocaChallenge.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("api/v1/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

@GetMapping("/getAll")
public List<Employee> getAllEmployee()
{
    return employeeService.getAllEmployee();
}


@PostMapping("/Company/{companyId}/save")
public ResponseEntity<String> save(@RequestBody Employee employee, @PathVariable ("companyId") Integer  companyId ) {

    try {
        Company company = companyService.GetCompanyById(companyId);
        company.getEmployee().add(employee);
        employeeService.SaveEmployee(employee);
        companyService.SaveCompany(company);
        String nameOfEmployee = employee.getName();
        return new ResponseEntity<>(nameOfEmployee + " has been added to "+company.getName(), HttpStatus.CREATED);
    }
    catch (Exception e){
        return new ResponseEntity<>("Could not add Employee", HttpStatus.BAD_REQUEST);
    }
}


    @PutMapping("edit/{id}")
    public ResponseEntity <Response> UpdateEmployee(@PathVariable int id, @RequestBody Employee employee)
    {
        Response response = new Response();
        employeeService.UpdateEmployee(employee, id);
        try {
            String EmployeeName = employee.getName();
            Integer EmployeeId = employee.getId();
            response.setStatusCode("200");
            response.setStatusMsg("Employee named "+EmployeeName+ " with employee Id " +EmployeeId+ " has bee successfully updated");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("Employee not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> DeleteEmployee (@PathVariable int id, @RequestBody Employee employee)
    {
        Response response = new Response();
        try{

            String EmployeeName = employee.getName();

            employeeService.DeleteEmployee(id);
            response.setStatusCode("200");
            response.setStatusMsg( EmployeeName + " named employee successfully deleted");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("Employee not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }


    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable ("id") Integer IntegerId)
    {
        Employee employee = new Employee();
        employee = employeeService.getEmployeeById(IntegerId);
        Response response = new Response();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employee);

    }


    @GetMapping("/updateSalary/{age}/{workingYear}")
    public List<Employee> findByAgeAndWorkingYear(@PathVariable("age") Integer age, @PathVariable("workingYear") Integer workingYear){
        return employeeService.findByAgeAndWorkingYear(age, workingYear);
    }
}
