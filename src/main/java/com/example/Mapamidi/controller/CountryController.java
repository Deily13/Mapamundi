package com.example.Mapamidi.controller;

import com.example.Mapamidi.model.Country;
import com.example.Mapamidi.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mapamundi")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country")
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }
}

