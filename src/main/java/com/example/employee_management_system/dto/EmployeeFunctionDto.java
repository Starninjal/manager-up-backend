package com.example.employee_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeFunctionDto {
    private Long employeeFunctionId;
    private String name;
    private String description;
    @JsonIgnore
    private List<EmployeeDto> employeeDtoList;
}