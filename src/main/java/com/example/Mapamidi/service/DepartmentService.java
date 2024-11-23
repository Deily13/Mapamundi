package com.example.Mapamidi.service;

import com.example.Mapamidi.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findDepartmentByCountryId(Long countryId);
}
