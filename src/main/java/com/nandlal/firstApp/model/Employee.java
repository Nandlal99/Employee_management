package com.nandlal.firstApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;
    private String empName;
    private String empAddress;
    private String empDOB;
    private String empContactNumber;

    public Employee() {
    }

    public Employee(String empName, String empAddress, String empDOB, String empContactNumber) {
        this.empName = empName;
        this.empAddress = empAddress;
        this.empDOB = empDOB;
        this.empContactNumber = empContactNumber;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpDOB() {
        return empDOB;
    }

    public void setEmpDOB(String empDOB) {
        this.empDOB = empDOB;
    }

    public String getEmpContactNumber() {
        return empContactNumber;
    }

    public void setEmpContactNumber(String empContactNumber) {
        this.empContactNumber = empContactNumber;
    }
}
