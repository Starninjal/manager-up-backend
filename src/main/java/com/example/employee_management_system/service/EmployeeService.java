package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.EmployeeDto;
import com.example.employee_management_system.interfaces.IService;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.model.EmployeeFunction;
import com.example.employee_management_system.repository.EmployeeRepository;
import com.example.employee_management_system.util.EmployeeUtil;
import com.example.employee_management_system.util.NumberFormatter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IService<Employee, EmployeeDto> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(EmployeeDto dto) {
        return null;
    }

    @Override
    public Employee update(EmployeeDto dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if (id != null &&  employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entity was not found because id is null or entity was not found in the database!");
        }
    }

    public List<Employee> increaseAllEmployeeSalaryBy(Double percentage) {
        employeeRepository.increaseAllSalariesBy(percentage);
        return employeeRepository.findAll();
    }

    public Map<EmployeeFunction, List<Employee>> groupEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().filter(employee -> employee.getEmployeeFunction() != null)
                .collect(Collectors.groupingBy(Employee::getEmployeeFunction));
    }

    public Map<String, List<EmployeeDto>> groupEmployeesByFunctionName() {
        return findAll().stream()
                .filter(employee -> employee.getEmployeeFunction() != null)
                .collect(Collectors.groupingBy(
                        employee -> employee.getEmployeeFunction().getName(),
                        Collectors.mapping(
                                employee -> employeeMapper.toDto(employee, true),
                                Collectors.toList()
                        )
                ));
    }

    public Map<Integer, List<EmployeeDto>> groupMinimumWageCountByEmployees() {
        return findAll().stream()
                .collect(
                        Collectors.groupingBy(employee -> employeeMinimumWageCountBy(employee.getSalary()),
                                Collectors.mapping(
                                        e -> employeeMapper.toDto(e, true),
                                        Collectors.toList()
                                ))
                );
    }

    public List<Employee> findAllEmployeesWhenBirthDateBetween(Integer startMonthValue, Integer endMonthValue) {
        return findAll().stream()
                .filter(employee -> employee.getBirthDate().getMonthValue() >= startMonthValue && employee.getBirthDate().getMonthValue() <= endMonthValue)
                .toList();
    }

    public Employee findEmployeeWithMaxAge() {
        return employeeMapper.toModel(Collections.max(employeeMapper.toListDto(findAll()), Comparator.comparingInt(EmployeeDto::getAge)));
    }

    public List<Employee> findSortedEmployeesByName() {
        List<Employee> employeeList = employeeRepository.findAll();
        employeeList.sort(Comparator.comparing(Employee::getName));
        return employeeList;
    }

    public String sumAllEmployeesSalary() {
        return NumberFormatter.format(findAll().stream().mapToDouble(Employee::getSalary).sum());
    }

    public Integer employeeMinimumWageCountBy(Double salary) {
        return (int) (salary / EmployeeUtil.EMPLOYEE_MINIMUM_WAGE);
    }
}