package com.example.Mapamidi.service.serviceImpl;

import com.example.Mapamidi.model.Department;
import com.example.Mapamidi.repository.DepartmentRepository;
import com.example.Mapamidi.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private  final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findDepartmentByCountryId(Long countryId) {
        return departmentRepository.findDepartmentByCountry_Id(countryId);
    }
}
