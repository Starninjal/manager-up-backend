package com.example.employee_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeFunctionId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "employeeFunction", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employeeList;
}
