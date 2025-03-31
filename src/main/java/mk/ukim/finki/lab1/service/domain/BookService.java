package mk.ukim.finki.lab1.service;


import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();

    Optional<Book> save(BookDto book);

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, BookDto book);

    Optional<Book> rent(Long id, String username);

    List<Book> findByCategory(Category category);

    List<Book> findByNameAndAuthorAndDescription(String name, Long authorId, String description);

    List<Book> findRelatedAuthorOrCategory(Long authorId, Category category);

    void deleteById(Long id);


}
