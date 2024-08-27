package com.nandlal.firstApp.repository;

import com.nandlal.firstApp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepoistory extends JpaRepository<Employee, Long> {

    //    Page<Employee> findByEmpNameContaining(String empName, Pageable pageable);
}
