package com.example.Mapamidi.controller;

import com.example.Mapamidi.model.Department;
import com.example.Mapamidi.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department/{countryId}")
    public List<Department> getAllDepartmentByIdCountry(
            @PathVariable long countryId
    ) {
        return departmentService.findDepartmentByCountryId(countryId);
    }

    @GetMapping("/count")
    public ResponseEntity<String> countDepartmentsByCountry(@RequestParam String countryName) {
        try {
            long count = departmentService.countByCountryName(countryName);

            return ResponseEntity.ok("El país '" + countryName + "' tiene " + count + " departamentos.");
        } catch (IllegalArgumentException e) {
            // Maneja errores de entrada
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Manejo general de errores inesperados
            return ResponseEntity.status(500).body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


}
