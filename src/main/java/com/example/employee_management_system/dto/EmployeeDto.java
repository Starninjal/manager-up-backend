package com.example.employee_management_system.dto;

import com.example.employee_management_system.util.DateFormatter;
import com.example.employee_management_system.util.NumberFormatter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmployeeDto extends PersonData {
    private Long employeeId;
    private BigDecimal salary;
    private EmployeeFunctionDto employeeFunctionDto;

    @JsonProperty("salary")
    public String getSalaryFormatted() {
        return NumberFormatter.format(salary);
    }

    @JsonProperty("birthDate")
    public String getBirthDateFormatted() {
        return DateFormatter.format(getBirthDate());
    }

    @JsonProperty("age")
    public Integer getAge() {
        if (getBirthDate() == null) {
            return null;
        }
        return Period.between(getBirthDate(), LocalDate.now()).getYears();
    }
}
