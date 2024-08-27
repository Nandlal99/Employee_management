package com.nandlal.firstApp.controller;

import com.nandlal.firstApp.Exception.ResourceNotFoundException;
import com.nandlal.firstApp.model.Employee;
import com.nandlal.firstApp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Employee", description = "Employee management APIs")
@RestController
@RequestMapping("/api/employee")
public class MainController {

    private final EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Retrieve all the Employees",
            description = "Get all the Employee objects. The response is a list of Employee object with empId, empName, " +
                    "empAddress, empDOB and empContactNumber.",
            tags = {"employee", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content( schema = @Schema(implementation = Employee.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllEmployees(
//            @RequestParam(required = false) String empName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){

       Map<String, Object> employees = employeeService.findAllEmployees(page, size);
       return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve an Employee by Id",
            description = "Get a Employee object by specifying its id. The response is a Employee object with empId, empName, " +
                    "empAddress, empDOB and empContactNumber.",
            tags = {"employee", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content( schema = @Schema(implementation = Employee.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Invalid employee id"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Operation(
            summary = "Register a new Employee",
            description = "Register a new Employee. The request body is Employee object with empName," +
                    "empAddress, empDOB and empContactNumber. The response is a message.",
            tags = {"employee", "post"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee is registered successfully !!!"),
            @ApiResponse(responseCode = "404", description = "Invalid Employee data"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PostMapping("/add")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return new ResponseEntity<>("Employee is registered successfully !!!", HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update details of an Employee",
            description = "Update details of an Employee by specifying by its id. The request body is Employee object with empName," +
                    "empAddress, empDOB and empContactNumber. The response is a message.",
            tags = {"employee", "put"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee details is updated !!!"),
            @ApiResponse(responseCode = "404", description = "Invalid Employee data"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,
                                                 @PathVariable long id){
        Boolean isUpdated = employeeService.updateEmployee(employee,id);
        if (!isUpdated){
            throw new ResourceNotFoundException("Invalid employee id");
        }
        return new ResponseEntity<>("Employee details is updated !!!", HttpStatus.OK);
    }

    @Operation(
            summary = "Delete an Employee",
            description = "Delete of an Employee by specifying by its id. The response is a message.",
            tags = {"employee", "delete"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee of id is deleted !!!"),
            @ApiResponse(responseCode = "404", description = "Invalid Employee data"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){
        Boolean isDelete = employeeService.deleteEmployee(id);
        if(!isDelete){
            throw new ResourceNotFoundException("Employee is not found of Id: "+id);
        }
        return new ResponseEntity<>("Employee of Id: "+id+" is deleted !!!", HttpStatus.OK);
    }

}
