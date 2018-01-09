package com.momo;

public class Employee {

    private String firstName;
    private String lastName;
    private double salary;
    private String department;

    public Employee(String firstName, String lastName, double salary, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }
    // Returns first name
    public String getFirstName() {
        return firstName;
    }

    // Returns last name
    public String getLastName() {
        return lastName;
    }
    // Returns salary
    public double getSalary() {
        return salary;
    }

    // Returns department
    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("%-14s %-14s %-8.2f%s", getFirstName(), getLastName(), getSalary(), getDepartment());
    }
}
