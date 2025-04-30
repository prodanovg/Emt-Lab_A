package mk.ukim.finki.lab1.config;

import jakarta.annotation.PostConstruct;

import mk.ukim.finki.lab1.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.lab1.repository.BooksPerAuthorViewRepository;
import org.springframework.stereotype.Component;


@Component
public class MaterializedViewRefresher {
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public MaterializedViewRefresher(BooksPerAuthorViewRepository booksPerAuthorViewRepository, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @PostConstruct
    public void init() {
        booksPerAuthorViewRepository.refreshMaterializedView();
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
