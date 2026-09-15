package com.luv2code.cruddemo.service;


import com.luv2code.cruddemo.Dao.EmployeeDAO;
import com.luv2code.cruddemo.Entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    //Inject Employee DAO
    // Sert uup constructor Injection
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDao){
        employeeDAO=theEmployeeDao;
    }


    //find All method
    @Override
    public List<Employee> findAll(){
        return  employeeDAO.findAll();
    }

    //FIND BY ID
    @Override
    public Employee findById(int Id) {
        return employeeDAO.findById(Id);
    }

    //SAVE >> ADD + UPDATE
    @Override
    @Transactional
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    //DELETE
    @Override
    @Transactional
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }


}
