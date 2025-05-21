package mk.ukim.finki.lab1.service.application.impl;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.dto.CreateBookDto;
import mk.ukim.finki.lab1.model.dto.DisplayBookDto;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.service.application.BookApplicationService;
import mk.ukim.finki.lab1.service.domain.AuthorService;
import mk.ukim.finki.lab1.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.authorId());

        return bookService.save(createBookDto.toBook(author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.authorId());

        return  bookService.update(id, createBookDto.toBook(author.orElse(null))).map(DisplayBookDto::from);
    }

//    @Override
//    public Optional<DisplayBookDto> rent(Long id) {
//        return bookService.rent(id).map(DisplayBookDto::from);
//    }

    @Override
    public List<DisplayBookDto> findByCategory(Category category) {
        return DisplayBookDto.from(bookService.findByCategory(category));
    }

    @Override
    public List<DisplayBookDto> findByNameAndAuthorAndDescription(String name, Long authorId, String description) {
        return DisplayBookDto.from(bookService.findByNameAndAuthorAndDescription(name, authorId, description));
    }

    @Override
    public List<DisplayBookDto> findRelatedAuthorOrCategory(Long authorId, Category category) {
        return DisplayBookDto.from(bookService.findRelatedAuthorOrCategory(authorId, category));
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public DisplayBookDto findByName(String name) {
        return DisplayBookDto.from(bookService.findByName(name));
    }
}
