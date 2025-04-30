package mk.ukim.finki.lab1.service.application.impl;

import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.dto.CreateAuthorDto;
import mk.ukim.finki.lab1.model.dto.DisplayAuthorDto;
import mk.ukim.finki.lab1.model.views.BooksPerAuthorView;
import mk.ukim.finki.lab1.repository.AuthorRepository;
import mk.ukim.finki.lab1.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.lab1.service.application.AuthorApplicationService;
import mk.ukim.finki.lab1.service.domain.AuthorService;
import mk.ukim.finki.lab1.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDtoDto) {
        Optional<Country> country = countryService.findById(createAuthorDtoDto.countryId());
        return authorService.save(createAuthorDtoDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDtoDto) {
        Optional<Country> country = countryService.findById(createAuthorDtoDto.countryId());

        return authorService.update(id, createAuthorDtoDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

    //Views

    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView findBooksPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();

    }
}
