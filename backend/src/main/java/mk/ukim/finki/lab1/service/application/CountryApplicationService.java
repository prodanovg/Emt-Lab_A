package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.dto.CreateCountryDto;
import mk.ukim.finki.lab1.model.dto.DisplayCountryDto;
import mk.ukim.finki.lab1.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    void deleteById(Long id);

    void refreshMaterializedView();

    List<AuthorsPerCountryView> findAllAuthorsPerCountry();

    AuthorsPerCountryView findAuthorsPerCountry(Long id);
}
