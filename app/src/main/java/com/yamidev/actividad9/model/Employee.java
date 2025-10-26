package com.yamidev.actividad9.model;

public class Employee {
    private long id;
    private String name;
    private String position;
    private double salary;



    public Employee(long id, double salary, String position, String name) {
        this.id = id;
        this.salary = salary;
        this.position = position;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}


