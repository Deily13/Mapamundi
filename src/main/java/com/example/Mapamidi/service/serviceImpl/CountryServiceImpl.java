package com.example.Mapamidi.service.serviceImpl;

import com.example.Mapamidi.model.Country;
import com.example.Mapamidi.repository.CountryRepository;
import com.example.Mapamidi.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Country findById(long id) {
        return null;
    }
}
