package mk.ukim.finki.lab1.service;


import mk.ukim.finki.lab1.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> save(AuthorDto author);

    Optional<Author> findById(Long id);

    Optional<Author> update(Long id, AuthorDto author);

    void deleteById(Long id);

}
