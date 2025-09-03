package com.example.employee_management_system.repository;

import com.example.employee_management_system.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.salary = e.salary + (e.salary * :percentage / 100)")
    void increaseAllSalariesBy(@Param("percentage") Double percentage);
}
