package com.example.Mapamidi.controller;

import com.example.Mapamidi.dto.CountryResponseDto;
import com.example.Mapamidi.model.Country;
import com.example.Mapamidi.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mapamundi")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/listCountry")
    public ResponseEntity<Page<CountryResponseDto>> getCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CountryResponseDto> countries = countryService.getCountriesWithIdAndName(page, size);
        return ResponseEntity.ok(countries);
    }


    @GetMapping("/{id}")
    public String getCountryName(@PathVariable long id) {
        Country country = countryService.findById(id);
        return "El nombre del país es: " + country.getName();
    }


    @GetMapping("/country/{name}")
    public CountryResponseDto getCountryByName(@PathVariable String name) {
        return countryService.getCountryIdByName(name);
    }
}

