package com.example.employee_management_system.repository;

import com.example.employee_management_system.model.EmployeeFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeFunctionRepository extends JpaRepository<EmployeeFunction, Long> {
    Optional<EmployeeFunction> findByName(String name);
}
