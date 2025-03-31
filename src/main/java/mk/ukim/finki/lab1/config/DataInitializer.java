package mk.ukim.finki.lab1.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.model.enumerations.Role;
import mk.ukim.finki.lab1.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import mk.ukim.finki.lab1.repository.BookRepository;
@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        bookRepository.save(new Book("Book 1", "test", category1, authorRepository.findById(1L).get(), 100, false));
        bookRepository.save(new Book("Book 2", "1234", category2, authorRepository.findById(2L).get(), 500, false));

        userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "Gorge",
                "Prodanov",
                Role.ROLE_LIBRARIAN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }
}

