package com.example.employee_management_system.loader;

import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.model.EmployeeFunction;
import com.example.employee_management_system.repository.EmployeeFunctionRepository;
import com.example.employee_management_system.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, EmployeeFunctionRepository employeeFunctionRepository) {
        return args -> {
            if (employeeFunctionRepository.count() == 0) {
                loadEmployeeFunctions(employeeFunctionRepository);
            }

            if (employeeRepository.count() == 0) {
                loadEmployees(employeeRepository, employeeFunctionRepository);
            }
        };
    }

    private void loadEmployeeFunctions(EmployeeFunctionRepository employeeFunctionRepository) {
        List<EmployeeFunction> functions = List.of(
                EmployeeFunction.builder()
                        .name("Operador")
                        .description("Operador de equipamentos e processos")
                        .build(),
                EmployeeFunction.builder()
                        .name("Coordenador")
                        .description("Coordenação de equipes e atividades")
                        .build(),
                EmployeeFunction.builder()
                        .name("Diretor")
                        .description("Diretoria executiva da empresa")
                        .build(),
                EmployeeFunction.builder()
                        .name("Recepcionista")
                        .description("Atendimento ao público e recepção")
                        .build(),
                EmployeeFunction.builder()
                        .name("Contador")
                        .description("Contabilidade e finanças")
                        .build(),
                EmployeeFunction.builder()
                        .name("Gerente")
                        .description("Gestão de departamentos e equipes")
                        .build(),
                EmployeeFunction.builder()
                        .name("Eletricista")
                        .description("Instalação e manutenção elétrica")
                        .build()
        );

        employeeFunctionRepository.saveAll(functions);
        System.out.println("Funções de funcionário carregadas com sucesso! Total: " + employeeFunctionRepository.count());
    }

    private void loadEmployees(EmployeeRepository employeeRepository, EmployeeFunctionRepository employeeFunctionRepository) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Optional<EmployeeFunction> operatorOpt = employeeFunctionRepository.findByName("Operador");
        Optional<EmployeeFunction> coordenadorOpt = employeeFunctionRepository.findByName("Coordenador");
        Optional<EmployeeFunction> diretorOpt = employeeFunctionRepository.findByName("Diretor");
        Optional<EmployeeFunction> recepcionistaOpt = employeeFunctionRepository.findByName("Recepcionista");
        Optional<EmployeeFunction> contadorOpt = employeeFunctionRepository.findByName("Contador");
        Optional<EmployeeFunction> gerenteOpt = employeeFunctionRepository.findByName("Gerente");
        Optional<EmployeeFunction> eletricistaOpt = employeeFunctionRepository.findByName("Eletricista");

        if ( operatorOpt.isPresent() && coordenadorOpt.isPresent() &&
                diretorOpt.isPresent() && recepcionistaOpt.isPresent() && contadorOpt.isPresent() &&
                gerenteOpt.isPresent() && eletricistaOpt.isPresent()) {

            List<Employee> employees = List.of(
                    Employee.builder()
                            .name("Maria")
                            .birthDate(LocalDate.parse("18/10/2000", formatter))
                            .salary(BigDecimal.valueOf(2008.44))
                            .employeeFunction(operatorOpt.get())
                            .build(),

                    Employee.builder()
                            .name("João")
                            .birthDate(LocalDate.parse("12/05/1990", formatter))
                            .salary(BigDecimal.valueOf(2284.38))
                            .employeeFunction(operatorOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Caio")
                            .birthDate(LocalDate.parse("02/05/1961", formatter))
                            .salary(BigDecimal.valueOf(9885.14))
                            .employeeFunction(coordenadorOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Miguel")
                            .birthDate(LocalDate.parse("14/10/1988", formatter))
                            .salary(BigDecimal.valueOf(19119.88))
                            .employeeFunction(diretorOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Alice")
                            .birthDate(LocalDate.parse("05/01/1995", formatter))
                            .salary(BigDecimal.valueOf(2234.68))
                            .employeeFunction(recepcionistaOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Heltor")
                            .birthDate(LocalDate.parse("19/11/1999", formatter))
                            .salary(BigDecimal.valueOf(1582.72))
                            .employeeFunction(operatorOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Arthur")
                            .birthDate(LocalDate.parse("31/03/1993", formatter))
                            .salary(BigDecimal.valueOf(4071.84))
                            .employeeFunction(contadorOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Laura")
                            .birthDate(LocalDate.parse("08/03/1994", formatter))
                            .salary(BigDecimal.valueOf(3017.45))
                            .employeeFunction(gerenteOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Heloisa")
                            .birthDate(LocalDate.parse("24/05/2003", formatter))
                            .salary(BigDecimal.valueOf(1806.85))
                            .employeeFunction(eletricistaOpt.get())
                            .build(),

                    Employee.builder()
                            .name("Helena")
                            .birthDate(LocalDate.parse("02/09/1996", formatter))
                            .salary(BigDecimal.valueOf(2799.89))
                            .employeeFunction(gerenteOpt.get())
                            .build()
            );

            employeeRepository.saveAll(employees);
            System.out.println("Funcionários carregados com sucesso! Total: " + employeeRepository.count());
        } else {
            System.out.println("Não foi possível carregar funcionários. Funções não encontradas.");
        }
    }
}