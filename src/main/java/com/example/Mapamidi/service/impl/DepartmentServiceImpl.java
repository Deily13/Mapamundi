package com.example.Mapamidi.service.impl;

import com.example.Mapamidi.model.Department;
import com.example.Mapamidi.repository.DepartmentRepository;
import com.example.Mapamidi.service.DepartmentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    private final EntityManager entityManager;

    @Override
    public List<Department> findDepartmentByCountryId(Long countryId) {
        return departmentRepository.findDepartmentByCountry_Id(countryId);
    }

    @Override
    public long countByCountryName(String countryName) {
        // Validación del parámetro
        if (countryName == null || countryName.isBlank()) {
            throw new IllegalArgumentException("El nombre del país no puede estar vacío");
        }

        // Consulta JPQL para contar departamentos por nombre de país
        String jpql = "SELECT COUNT(d.id) FROM Department d WHERE d.country.name = :countryName";
        return entityManager.createQuery(jpql, Long.class)
                .setParameter("countryName", countryName.trim())
                .getSingleResult();
    }
}
