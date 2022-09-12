package com.example.EnocaChallenge.CompanyService;

import com.example.EnocaChallenge.Entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

      Company SaveCompany (Company company);
      Company GetCompanyById(Integer id);
      Company UpdateCompany(Company company, Integer id);
      void DeleteCompany (Integer id);
      List<Company> getAllCompany();

}
