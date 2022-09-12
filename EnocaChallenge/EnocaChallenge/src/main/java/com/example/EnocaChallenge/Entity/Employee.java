package com.example.EnocaChallenge.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "employees")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;


    @Column(name = "salary")
    private double salary;

    @Column(name = "working_year")
    private Integer working_year;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="company_id")
    private Company company;


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorking_year() {
        return working_year;
    }

    public void setWorking_year(Integer working_year) {
        this.working_year = working_year;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary=salary;
    }


}
