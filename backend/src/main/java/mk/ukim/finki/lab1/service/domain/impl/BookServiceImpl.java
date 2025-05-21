package mk.ukim.finki.lab1.service.domain.impl;


import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.model.exceptions.BookAlreadyRented;
import mk.ukim.finki.lab1.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab1.repository.BookRepository;
import mk.ukim.finki.lab1.service.domain.AuthorService;
import mk.ukim.finki.lab1.service.domain.BookService;
import mk.ukim.finki.lab1.service.domain.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final UserService userService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, UserService userService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.userService = userService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
            Author author = authorService.findById(book.getAuthor().getId()).get();
            Category category = Category.valueOf(book.getCategory().toString());


            return Optional.of(bookRepository.save(new Book(
                    book.getId(),
                    book.getName(),
                    book.getDescription(),
                    category,
                    author,
                    book.getAvailableCopies()
            )));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(existingProduct -> {
            if (book.getName() != null) {
                existingProduct.setName(book.getName());
            }
            if (book.getCategory() != null) {
                existingProduct.setCategory(Category.valueOf(book.getCategory().toString()));
            }
            if (book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
                existingProduct.setAuthor(authorService.findById(book.getAuthor().getId()).get());
            }
            if (book.getAvailableCopies() != 0) {
                existingProduct.setAvailableCopies(book.getAvailableCopies());
            }
            return bookRepository.save(existingProduct);
        });
    }

//    //4
//    @Override
//    public Optional<Book> rent(Long id) {
//        Optional<Book> book = bookRepository.findById(id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        return book.map(existingBook -> {
//            if (existingBook.getRented() == false) {
//                existingBook.setRented(true);
//                existingBook.setRentedBy(username);
//                return bookRepository.save(existingBook);
//            } else {
//                throw new BookAlreadyRented();
//            }
//        });
//    }


    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    //1
    @Override
    public List<Book> findByNameAndAuthorAndDescription(String name, Long authorId, String description) {
        return bookRepository.findAllByNameAndAuthorIdAndDescription(name, authorId, description);
    }

    //2
    @Override
    public List<Book> findRelatedAuthorOrCategory(Long authorId, Category category) {
        return bookRepository.findAllByAuthorIdOrCategory(authorId, category);
    }

    //5 Soft delete
    @Override
    public void deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book existingBook = book.get();
            existingBook.setDeleted(true);
            bookRepository.save(existingBook);
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }
    // Hard delete
//    @Override
//    public void deleteById(Long id){
//        bookRepository.deleteById(id);
//    }
}
