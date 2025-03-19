package mk.ukim.finki.lab1.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.Author;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.model.Country;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.repository.AuthorRepository;
import mk.ukim.finki.lab1.repository.BookRepository;
import mk.ukim.finki.lab1.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country c1 = countryRepository.save(new Country("Germany", "Europe"));
        Country c2 = countryRepository.save(new Country("United Kingdom", "Europe"));
        Country c3 = countryRepository.save(new Country("United States", "North America"));
        Country c4 = countryRepository.save(new Country("Japan", "Asia"));

        authorRepository.save(new Author("test", "testt", c1));
        authorRepository.save(new Author("test1", "testt1", c2));
        authorRepository.save(new Author("test2", "testt2", c3));
        authorRepository.save(new Author("test3", "testt3", c4));

        Category category1 = Category.valueOf("NOVEL");
        Category category2 = Category.valueOf("FANTASY");
        bookRepository.save(new Book("Book 1", category1, authorRepository.findById(1L).get(), 100, "test", false));
        bookRepository.save(new Book("Book 2",category2, authorRepository.findById(2L).get(), 500, "1234", false));
    }
}

