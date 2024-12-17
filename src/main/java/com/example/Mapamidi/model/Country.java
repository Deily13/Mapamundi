package com.example.Mapamidi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "country")
@Data
public class Country {

    @Id
    @Column(name = "id_country", length = 3, nullable = false)
    private long id;

    @Column(name = "iso3", length = 3, nullable = false, unique = true)
    private String iso3;

    @Column(name = "country_name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Department> departments;
}
