package com.nandlal.firstApp.service;

import com.nandlal.firstApp.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {


    Map<String, Object> findAllEmployees(int page, int size);

    Employee findEmployeeById(long id);

    void createEmployee(Employee employee);

    Boolean updateEmployee(Employee employee, long id);

    Boolean deleteEmployee(long id);
}
