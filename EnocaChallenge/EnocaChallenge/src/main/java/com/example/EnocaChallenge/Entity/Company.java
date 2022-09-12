package com.example.EnocaChallenge.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "company_id")
    private Integer Id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employee;

    public Company(String name, List<Employee> employee) {
        this.name = name;
        this.employee = employee;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public void SetId(Integer id){
        this.Id=id;
    }

    public Integer getId() {
        return Id ;
    }
}


