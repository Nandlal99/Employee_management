package com.nandlal.firstApp.service;

import com.nandlal.firstApp.Exception.ResourceNotFoundException;
import com.nandlal.firstApp.model.Employee;
import com.nandlal.firstApp.repository.EmployeeRepoistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImp implements EmployeeService{

    EmployeeRepoistory employeeRepoistory;

    public EmployeeServiceImp(EmployeeRepoistory employeeRepoistory) {
        this.employeeRepoistory = employeeRepoistory;
    }

    @Override
    public Map<String, Object> findAllEmployees(int page, int size) {

        List<Employee> employees = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<Employee> pageTuts;

        pageTuts = employeeRepoistory.findAll(paging);
        employees = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employees);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());
        return response;
    }

    @Override
    public Employee findEmployeeById(long id) {
        Optional<Employee> employee = employeeRepoistory.findById(id);
        if(employee.isEmpty()){
            throw new ResourceNotFoundException("Employee is not found of Id: "+ id);
        }
        return employee.get();
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepoistory.save(employee);
    }

    @Override
    public Boolean updateEmployee(Employee employee, long id) {
        Optional<Employee> employee1 = employeeRepoistory.findById(id);
        if(employee1.isEmpty()){
            return false;
        }

        Employee emp = employee1.get();
        emp.setEmpName(employee.getEmpName());
        emp.setEmpAddress(employee.getEmpAddress());
        emp.setEmpDOB(employee.getEmpDOB());
        emp.setEmpContactNumber(employee.getEmpContactNumber());
        employeeRepoistory.save(emp);
        return true;
    }

    @Override
    public Boolean deleteEmployee(long id) {
        Optional<Employee> employee1 = employeeRepoistory.findById(id);
        if(employee1.isEmpty()){
            return false;
        }
        employeeRepoistory.delete(employee1.get());
        return true;
    }
}
