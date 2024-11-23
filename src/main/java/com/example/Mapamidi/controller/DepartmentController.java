package com.example.Mapamidi.controller;

import com.example.Mapamidi.model.Department;
import com.example.Mapamidi.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{countryId}")
    public List<Department> getAllDepartmentByIdCountry(
            @PathVariable long countryId
    ) {
        return departmentService.findDepartmentByCountryId(countryId);
    }

}
