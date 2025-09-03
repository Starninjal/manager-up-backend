package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.EmployeeFunctionDto;
import com.example.employee_management_system.model.EmployeeFunction;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFunctionMapper {
    public EmployeeFunction toModel(EmployeeFunctionDto dto) {
        return EmployeeFunction.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public EmployeeFunctionDto toDto(EmployeeFunction model, boolean requiresId) {
        return EmployeeFunctionDto.builder()
                .employeeFunctionId(requiresId ? model.getEmployeeFunctionId() : null)
                .name(model.getName())
                .description(model.getDescription())
                .build();

    }
}
