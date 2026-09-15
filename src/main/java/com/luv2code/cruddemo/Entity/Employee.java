package com.luv2code.cruddemo.Entity;

import jakarta.persistence.*;


@Entity
@Table(name="employee")
public class Employee {


    //defien fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)


    // Mapping with data base table column
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;


    //No argument conbstructor
    public Employee(){}

    //Parameterised Constructor
    public Employee(
                    String firstName ,
                    String lastName ,
                    String email)
    {

        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }

    //getter and setter

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
