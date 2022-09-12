package com.example.EnocaChallenge.CompanyController;

import com.example.EnocaChallenge.CompanyService.CompanyService;
import com.example.EnocaChallenge.Entity.Company;
import com.example.EnocaChallenge.Response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(path="api/v1/Company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    //Department kaydetme url define ediyoruz
    @PostMapping("/Save")
    public ResponseEntity<Response> SaveCompany(@RequestBody Company company)
    {

        companyService.SaveCompany(company);
        String name = company.getName();
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Company named "+ name+" saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/getAll")
    public List<Company> getAllCompany()
    {
        return companyService.getAllCompany();
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity <Response> UpdateCompany(@PathVariable Integer id, @RequestBody Company company)
    {
        Response response = new Response();
        companyService.UpdateCompany(company, id);
        try {
            String CompanyName = company.getName();
            response.setStatusCode("200");
            response.setStatusMsg("Company named "+CompanyName+" successfully updated");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("Company not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> DeleteCompany (@PathVariable Integer id)
    {
        Response response = new Response();
        try{
            companyService.DeleteCompany(id);
            response.setStatusCode("200");
            response.setStatusMsg("Company successfully deleted");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("Company not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<Company> GetCompanyById(@PathVariable ("id") Integer IntegerId)
    {
        Company company = new Company();
        company = companyService.GetCompanyById(IntegerId);
        Response response = new Response();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }



}
