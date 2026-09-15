package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int Id);

    public Employee save(Employee theEmployee);

    public void deleteById (int theId);

}
