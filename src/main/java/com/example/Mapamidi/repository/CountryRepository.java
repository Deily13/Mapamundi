package com.example.Mapamidi.repository;

import com.example.Mapamidi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findById(Long id);
    Country findByNameIgnoreCase(String countryName);

}
