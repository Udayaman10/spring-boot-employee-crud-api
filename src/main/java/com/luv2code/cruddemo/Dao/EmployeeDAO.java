package com.luv2code.cruddemo.Dao;

import com.luv2code.cruddemo.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    //Get Single Employee by i;
    Employee findById(int theId);

    //add or update the employee
    Employee save(Employee theEmployee);

    //delete a employee by id
    void deleteById(int Id);


}
