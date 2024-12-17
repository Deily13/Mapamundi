package com.example.Mapamidi.service.impl;

import com.example.Mapamidi.dto.CountryResponseDto;
import com.example.Mapamidi.model.Country;
import com.example.Mapamidi.repository.CountryRepository;
import com.example.Mapamidi.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Page<CountryResponseDto> getCountriesWithIdAndName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return countryRepository.findAll(pageable)
                .map(country -> new CountryResponseDto(country.getId(), country.getName()));
    }

    @Override
    public Country findById(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            return country.get();
        }else{
            throw new IllegalArgumentException("no se enecontro pais con id" + id);
        }
    }



    @Override
    public CountryResponseDto getCountryIdByName(String countryName) {
        Country country = countryRepository.findByNameIgnoreCase(countryName);

        if (country != null) {
            // Crear el DTO utilizando el constructor con parámetros
            return new CountryResponseDto(country.getId(), country.getName());
        } else {
            // En caso de que no se encuentre el país, manejar la excepción o devolver un DTO vacío
            return new CountryResponseDto(null, null);
        }
    }


}

