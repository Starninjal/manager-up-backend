package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.EmployeeDto;
import com.example.employee_management_system.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    @Autowired
    private EmployeeFunctionMapper employeeFunctionMapper;

    public Employee toModel(EmployeeDto dto) {
        return Employee.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .employeeFunction(employeeFunctionMapper.toModel(dto.getEmployeeFunctionDto()))
                .salary(dto.getSalary())
                .build();
    }

    public EmployeeDto toDto(Employee model, boolean requiresId) {
        return EmployeeDto.builder()
                .employeeId(requiresId ? model.getEmployeeId() : null)
                .name(model.getName())
                .birthDate(model.getBirthDate())
                .salary(model.getSalary())
                .employeeFunctionDto(employeeFunctionMapper.toDto(model.getEmployeeFunction(), true))
                .build();
    }

    public List<EmployeeDto> toListDto(List<Employee> employeeList) {
        return employeeList.stream().map(e -> toDto(e, false)).toList();
    }
}
