package com.example.Mapamidi.repository;

import com.example.Mapamidi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

Country findByName(String name);
}
