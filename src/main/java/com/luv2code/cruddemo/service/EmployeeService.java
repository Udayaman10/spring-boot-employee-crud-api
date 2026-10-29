package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.Entity.Employee;

import java.util.List;

public interface EmployeeService {

     List<Employee> findAll();

     Employee findById(int Id);

     Employee save(Employee theEmployee);

     void deleteById (int theId);


     List<Employee> saveAll(List<Employee> employess);
}
