package mk.ukim.finki.lab1.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab1.constants.JwtConstants;
import mk.ukim.finki.lab1.model.dto.*;
import mk.ukim.finki.lab1.model.enumerations.Category;
import mk.ukim.finki.lab1.service.application.BookApplicationService;
import mk.ukim.finki.lab1.service.application.UserBookApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final UserBookApplicationService userBookApplicationService;

    public BookController(BookApplicationService bookApplicationService, UserBookApplicationService userBookApplicationService) {
        this.bookApplicationService = bookApplicationService;
        this.userBookApplicationService = userBookApplicationService;
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String headerValue = request.getHeader(JwtConstants.HEADER);
        return headerValue.substring(JwtConstants.TOKEN_PREFIX.length());
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll() {
        List<DisplayBookDto> books = bookApplicationService.findAll();
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new book", description = "Creates a new book based on the given BookDto.")
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a book by ID using BookDto.")
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Optional: you can re-enable the rent endpoint when needed.
    /*
    @Operation(summary = "Rents a book", description = "Rents a book by its ID and username.")
    @PutMapping("/rent/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DisplayBookDto> rent(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        CreateUserBookDto createUserBookDto = new CreateUserBookDto(id, user.getUsername());
        return userBookApplicationService.save(createUserBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    */

    @Operation(summary = "List books by category", description = "List books by their category.")
    @GetMapping("/filter3")
    public ResponseEntity<List<DisplayBookDto>> findBooksByCategory(@RequestParam Category category) {
        return ResponseEntity.ok(bookApplicationService.findByCategory(category));
    }

    @Operation(summary = "List books by name, author, and description", description = "Filters books by name, author ID, and description.")
    @GetMapping("/filter1")
    public ResponseEntity<List<DisplayBookDto>> findBooksByNameAuthorAndDescription(@RequestParam String name,
                                                                                    @RequestParam Long authorId,
                                                                                    @RequestParam String description) {
        return ResponseEntity.ok(bookApplicationService.findByNameAndAuthorAndDescription(name, authorId, description));
    }

    @Operation(summary = "List books by author or category", description = "Filters books by author ID or category.")
    @GetMapping("/filter2")
    public ResponseEntity<List<DisplayBookDto>> findRelatedAuthorOrCategory(@RequestParam Long authorId,
                                                                            @RequestParam Category category) {
        return ResponseEntity.ok(bookApplicationService.findRelatedAuthorOrCategory(authorId, category));
    }
}
