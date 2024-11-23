package com.example.Mapamidi.service;

import com.example.Mapamidi.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findById(long id);
}
