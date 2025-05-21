package mk.ukim.finki.lab1.service.domain;


import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();

    Optional<Book> save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, Book book);

//    Optional<Book> rent(Long id);

    List<Book> findByCategory(Category category);

    List<Book> findByNameAndAuthorAndDescription(String name, Long authorId, String description);

    List<Book> findRelatedAuthorOrCategory(Long authorId, Category category);

    void deleteById(Long id);

    Book findByName(String name);

}
