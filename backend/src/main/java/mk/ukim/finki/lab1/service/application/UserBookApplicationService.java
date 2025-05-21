package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.UserBook;
import mk.ukim.finki.lab1.model.dto.*;

import java.util.List;
import java.util.Optional;

public interface UserBookApplicationService {

//    Optional<DisplayBookDto> save(CreateUserBookDto createUserBookDto);

    List<DisplayUserBookDto> findByUser(String username);

    List<DisplayUserBookDto> findByBook(Long bookId);

    DisplayBookDto getMostRentedBook();

    DisplayUserDto getUserWhoRentedMostBooks();

    DisplayAuthorDto getMostPopularAuthor();
}
