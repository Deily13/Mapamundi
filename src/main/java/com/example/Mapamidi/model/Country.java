package com.example.Mapamidi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "id_country", length = 3, nullable = false)
    private long id;

    @Column(name = "iso3", length = 3, nullable = false, unique = true) // CHAR(3), UNIQUE
    private String iso3;

    @Column(name = "country_name", nullable = false, length = 100) // VARCHAR(255)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Department> departments;
}
