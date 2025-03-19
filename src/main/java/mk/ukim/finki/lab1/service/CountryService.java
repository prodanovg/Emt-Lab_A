package mk.ukim.finki.lab1.service;


import mk.ukim.finki.lab1.model.Country;
import mk.ukim.finki.lab1.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> save(CountryDto country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, CountryDto country);

    void deleteById(Long id);
}
