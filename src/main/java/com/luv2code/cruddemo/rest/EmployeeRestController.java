package com.luv2code.cruddemo.rest;

import com.luv2code.cruddemo.Entity.Employee;
import com.luv2code.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController  {

    private EmployeeService employeeService;

    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService , JsonMapper theJsonMapper){
         employeeService=theEmployeeService;
         jsonMapper=theJsonMapper;
    }

    // exposes injection endpoints
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //Read new Employee by is  GET
    @GetMapping("/employees/{employeeId}")
    public  Employee findById(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee ==null){
            throw  new RuntimeException(("Employee is is not found-- "+employeeId));
        }
        return theEmployee;
    }

    //Add new Employee  POST

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //just in case id is passed by client , so need to forcefully make it zero
        // so that a new employee can save , instead of update

        // eg   POST => /api/employees/3
        // FORCE FULLY FOR THIS EMP SET ID TO 0
        // if use int use 0  , if Integer use null as it is wrrapper class
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // update employee PUT
    @PutMapping("/employees")
    public  Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //Deleet empoloyee DELETE

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
           Employee tempEmployee = employeeService.findById(employeeId);

           if(tempEmployee == null){
               throw new RuntimeException("Employee " + employeeId + " Not found!");
           }

           employeeService.deleteById(employeeId);
           return "Employee id -- "+employeeId+" deleted Succesfully!";
    }

    @PatchMapping("employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId ,
                                  @RequestBody Map<String , Object> patchPayLoad){


        Employee tempEmployee = employeeService.findById(employeeId);

        // if id does not exit
        if(tempEmployee==null){
            throw new RuntimeException("Employee id -"+employeeId +" not exist!");
        }
        // if try to update id
        if(patchPayLoad.containsKey("id")){
            throw new RuntimeException("Id is primary key , cant Update");
        }
         // patch the curr employee ith json passed vale
        Employee patchEmployee = jsonMapper.updateValue(tempEmployee,patchPayLoad);
        // save that patch id
        Employee dbEmployee= employeeService.save(patchEmployee);

        return dbEmployee;
    }

}
