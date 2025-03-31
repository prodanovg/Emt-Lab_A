package mk.ukim.finki.lab1.web;


import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.service.domain.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto book) {
        return bookService.save(book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto book) {
        return bookService.update(id, book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    //4
    @PutMapping("/rent/{id}")
    public ResponseEntity<Book> rent(@PathVariable Long id, @RequestParam String username) {
        return bookService.rent(id, username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter3")
    public List<Book> findBooksByCategory(@RequestParam Category category) {
        return bookService.findByCategory(category);
    }

    //1
    @GetMapping("/filter1")
    public List<Book> findBooksByNameAuthorAndDescription(@RequestParam String name,
                                                          @RequestParam Long authorId,
                                                          @RequestParam String description) {
        return bookService.findByNameAndAuthorAndDescription(name, authorId, description);
    }

    //2
    @GetMapping("/filter2")
    public List<Book> findRelatedAuthorOrCategory(@RequestParam Long authorId,
                                                  @RequestParam Category category) {
        return bookService.findRelatedAuthorOrCategory(authorId, category);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
