package com.luv2code.cruddemo.Dao;

import com.luv2code.cruddemo.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaIpl implements EmployeeDAO {

    //Define fields for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaIpl(EntityManager theentityManager){
        entityManager=theentityManager;
    }
    //the entityManager field now contains the EntityManager provided by Spring.

    @Override
    public List<Employee> findAll() {
        //create A query
        TypedQuery<Employee >theQuery = entityManager.createQuery(
                "From Employee",Employee.class);

        // execute the query and get retuirmn list
        List<Employee> Employees = theQuery.getResultList();

         return Employees;
    }



    @Override
    public Employee findById(int theId) {
        //get enmployee
        Employee theEmployee = entityManager.find(Employee.class , theId);
        //return
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //update or add empoyee
        // updated the passing id and store in db Data base and returning it
        Employee dbEmployee = entityManager.merge(theEmployee);
        //> if id==0 insert/save else update
        //return
        return dbEmployee;
    }

    @Override
    public void deleteById(int Id) {
        Employee theEmployee = entityManager.find(Employee.class , Id);

        entityManager.remove(theEmployee);
        return;

    }


}
