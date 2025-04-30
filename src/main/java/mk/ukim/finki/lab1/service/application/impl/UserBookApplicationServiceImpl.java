package mk.ukim.finki.lab1.service.application.impl;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.UserBook;
import mk.ukim.finki.lab1.model.dto.*;
import mk.ukim.finki.lab1.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab1.model.projections.UserProjection;
import mk.ukim.finki.lab1.service.application.UserBookApplicationService;
import mk.ukim.finki.lab1.service.domain.BookService;
import mk.ukim.finki.lab1.service.domain.UserBookService;
import mk.ukim.finki.lab1.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookApplicationServiceImpl implements UserBookApplicationService {

    private final UserBookService userBookService;
    private final UserService userService;
    private final BookService bookService;

    public UserBookApplicationServiceImpl(UserBookService userBookService, UserService userService, BookService bookService) {
        this.userBookService = userBookService;
        this.userService = userService;
        this.bookService = bookService;
    }

//    @Override
//    public DisplayUserBookDto save(CreateUserBookDto createUserBookDto) {
//        User user = userService.findByUsername(createUserBookDto.username());
//        Book book = bookService.findById(createUserBookDto.bookId())
//                .orElseThrow(() -> new BookNotFoundException(createUserBookDto.bookId()));
//
//        UserBook userBook = createUserBookDto.toUserBook(user, book);
//        userBook = userBookService.save(userBook);
//        return DisplayUserBookDto.from(userBook);
//
//    }

    @Override
    public List<DisplayUserBookDto> findByUser(String username) {
        return DisplayUserBookDto.from(userBookService.findByUser(username));
    }

    @Override
    public List<DisplayUserBookDto> findByBook(Long bookId) {
        return DisplayUserBookDto.from(userBookService.findByBook(bookId));
    }

    @Override
    public DisplayBookDto getMostRentedBook() {
        Book mostRentedBook = userBookService.getMostRentedBook();
        return DisplayBookDto.from(mostRentedBook);
    }

    @Override
    public DisplayUserDto getUserWhoRentedMostBooks() {
        User user = userBookService.getUserWhoRentedMostBooks();
        return DisplayUserDto.from(user);
    }

    @Override
    public DisplayAuthorDto getMostPopularAuthor() {
        Author author = userBookService.getMostPopularAuthor();
        return DisplayAuthorDto.from(author);
    }


}
