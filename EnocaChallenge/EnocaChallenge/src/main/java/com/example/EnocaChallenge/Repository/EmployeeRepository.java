package com.example.EnocaChallenge.Repository;

import com.example.EnocaChallenge.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e where e.age=:age and e.working_year=:working_year")
    List<Employee> findByAgeAndWorkingYear(@Param("age") Integer age, @Param("working_year") Integer working_year);
}
