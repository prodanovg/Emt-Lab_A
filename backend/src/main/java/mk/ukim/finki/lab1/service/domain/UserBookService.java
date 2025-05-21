package mk.ukim.finki.lab1.service.domain;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.UserBook;

import java.util.List;
import java.util.Optional;

public interface UserBookService {
//    Optional<UserBook> save(UserBook userBook);

    List<UserBook> findByUser(String username);

    List<UserBook> findByBook(Long bookId);

    Book getMostRentedBook();

    User getUserWhoRentedMostBooks();

    Author getMostPopularAuthor();
}
