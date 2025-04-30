package mk.ukim.finki.lab1.service.domain.impl;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.UserBook;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.repository.UserBookRepository;
import mk.ukim.finki.lab1.service.domain.BookService;
import mk.ukim.finki.lab1.service.domain.UserBookService;
import mk.ukim.finki.lab1.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserBookServiceImpl implements UserBookService {

    private final UserBookRepository userBookRepository;
    private final BookService bookService;
    private final UserService userService;

    public UserBookServiceImpl(UserBookRepository userBookRepository, BookService bookService, UserService userService) {
        this.userBookRepository = userBookRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

//    @Override
//    public Optional<UserBook> save(UserBook userBook) {
//
//        User user = userService.findByUsername(userBook.getUser().getUsername());
//        Optional<Book> book = bookService.findById(userBook.getId());
//
//        return userBookRepository.save(user,book);
//
//    }

    @Override
    public List<UserBook> findByUser(String username) {
        User user = userService.findByUsername(username);
        return userBookRepository.findByUser(user);
    }

    @Override
    public List<UserBook> findByBook(Long bookid) {
        Optional<Book> book = bookService.findById(bookid);
        return userBookRepository.findByBook(book);
    }


    @Override
    public Book getMostRentedBook() {
        return userBookRepository.findAll().stream()
                .collect(Collectors.groupingBy(userBook -> userBook.getBook(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No rented books found"));
    }


    @Override
    public User getUserWhoRentedMostBooks() {
        return userBookRepository.findAll().stream()
                .collect(Collectors.groupingBy(userBook -> userBook.getUser(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No users found"));
    }

    @Override
    public Author getMostPopularAuthor() {
        return userBookRepository.findAll().stream()
                .collect(Collectors.groupingBy(userBook -> userBook.getBook().getAuthor(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No authors found"));
    }

}
