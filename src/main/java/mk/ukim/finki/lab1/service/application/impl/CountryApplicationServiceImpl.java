package mk.ukim.finki.lab1.service.application.impl;

import mk.ukim.finki.lab1.model.dto.CreateCountryDto;
import mk.ukim.finki.lab1.model.dto.DisplayCountryDto;
import mk.ukim.finki.lab1.model.views.AuthorsPerCountryView;
import mk.ukim.finki.lab1.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.lab1.service.application.CountryApplicationService;
import mk.ukim.finki.lab1.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryService = countryService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto.toCountry())
                .map(DisplayCountryDto::from);

    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto) {
        return countryService.update(id, createCountryDto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

    //views

    @Override
    public List<AuthorsPerCountryView> findAllAuthorsPerCountry() {
        return authorsPerCountryViewRepository.findAll();
    }

    @Override
    public AuthorsPerCountryView findAuthorsPerCountry(Long id) {
        return authorsPerCountryViewRepository.findById(id).orElseThrow();
    }


    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();

    }
}
