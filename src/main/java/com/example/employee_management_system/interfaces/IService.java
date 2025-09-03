package com.example.employee_management_system.interfaces;

import java.util.List;

public interface IService <T, D> {
    T save(D dto);
    T update(D dto, Long id);
    void delete(Long id);
    List<T> findAll();
}
