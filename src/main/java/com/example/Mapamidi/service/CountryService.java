package com.example.Mapamidi.service;


import com.example.Mapamidi.dto.CountryResponseDto;
import com.example.Mapamidi.model.Country;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Page<CountryResponseDto> getCountriesWithIdAndName(int page, int size);

    Country findById(Long id);

    CountryResponseDto getCountryIdByName(String countryName);
}
