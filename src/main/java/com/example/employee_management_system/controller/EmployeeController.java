package com.example.employee_management_system.controller;

import com.example.employee_management_system.dto.EmployeeDto;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.repository.EmployeeRepository;
import com.example.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> getEmployeeList() {
        return ResponseEntity.ok(employeeMapper.toListDto(employeeService.findAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<List<EmployeeDto>> increaseAllEmployeesSalaryBy(@RequestParam Double increaseSalaryPercentage) {
        return ResponseEntity.ok(employeeMapper.toListDto(employeeService.increaseAllEmployeeSalaryBy(increaseSalaryPercentage)));
    }

    @GetMapping("/group-by-function-name")
    public ResponseEntity<Map<String, List<EmployeeDto>>> groupEmployeesByFunction() {
        return ResponseEntity.ok(employeeService.groupEmployeesByFunctionName());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployeesByBirthDateBetween(@RequestParam Integer startMonthValue, @RequestParam Integer endMonthValue) {
        return ResponseEntity.ok(employeeMapper.toListDto(employeeService.findAllEmployeesWhenBirthDateBetween(startMonthValue, endMonthValue)));
    }

    @GetMapping("/max-age")
    public ResponseEntity<EmployeeDto> findEmployeeWithMaxAge() {
        return ResponseEntity.ok(employeeMapper.toDto(employeeService.findEmployeeWithMaxAge(), true));
    }

    @GetMapping("/list/sorted")
    public ResponseEntity<List<EmployeeDto>> findEmployeesSortedByName() {
        return ResponseEntity.ok(employeeMapper.toListDto(employeeService.findSortedEmployeesByName()));
    }

    @GetMapping("/salary-sum")
    public ResponseEntity<String> getSalarySum() {
        return ResponseEntity.ok(employeeService.sumAllEmployeesSalary());
    }

    @GetMapping("/minimum-wage")
    public ResponseEntity<Map<Integer, List<EmployeeDto>>> groupMinimumWageByEmployees() {
        return ResponseEntity.ok(employeeService.groupMinimumWageCountByEmployees());
    }
}
