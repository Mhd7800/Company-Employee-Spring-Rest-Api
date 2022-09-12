package com.example.EnocaChallenge.CompanyService.CompanyServiceImpl;

import com.example.EnocaChallenge.Repository.CompanyRepository;
import com.example.EnocaChallenge.CompanyService.CompanyService;
import com.example.EnocaChallenge.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Company SaveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company GetCompanyById(Integer id) {
        return companyRepository.findById(id).get();
    }


    @Override
    public Company UpdateCompany(Company company, Integer id) {
        Company existingCompany = companyRepository.findById(id).get();
        if (null != existingCompany) {
            existingCompany.SetId(company.getId());
            existingCompany.setName(company.getName());
        }
        companyRepository.save(existingCompany);
        return existingCompany;

    }

    @Override
    public void DeleteCompany(Integer id) {
        Company existingCompany = companyRepository.findById(id).get();
        if (null != existingCompany) {
            companyRepository.deleteById(id);
        }
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }
}




