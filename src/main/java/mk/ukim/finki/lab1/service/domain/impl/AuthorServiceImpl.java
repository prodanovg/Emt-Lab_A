package mk.ukim.finki.lab1.service.impl;


import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.lab1.repository.AuthorRepository;
import mk.ukim.finki.lab1.service.AuthorService;
import mk.ukim.finki.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(AuthorDto author) {
        if (author.getCountry() != null
                && countryService.findById(author.getCountry()).isPresent()) {

            Country country = countryService.findById(author.getCountry()).get();

            return Optional.of(authorRepository.save(new Author(
                    author.getName(),
                    author.getSurname(),
                    country)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new));
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto author) {
        return authorRepository.findById(id).map(existingProduct -> {
            if (author.getName() != null) {
                existingProduct.setName(author.getName());
            }
            if (author.getSurname() != null) {
                existingProduct.setSurname(author.getSurname());
            }
            if (author.getCountry() != null && countryService.findById(author.getCountry()).isPresent()) {
                existingProduct.setCountry(countryService.findById(author.getCountry()).get());
            }
            return authorRepository.save(existingProduct);
        });
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
