package mk.ukim.finki.lab1.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.dto.DisplayBookDto;
import mk.ukim.finki.lab1.model.dto.WishListDto;
import mk.ukim.finki.lab1.service.application.BookApplicationService;
import mk.ukim.finki.lab1.service.application.UserApplicationService;
import mk.ukim.finki.lab1.service.application.WishListApplicationService;
import mk.ukim.finki.lab1.service.domain.UserBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "WishList API", description = "Endpoints for managing wishlists")
public class WishListController {
    private final BookApplicationService bookApplicationService;
    private final WishListApplicationService wishListApplicationService;
    private final UserApplicationService userApplicationService;
    private final UserBookService userBookService;

    public WishListController(BookApplicationService bookApplicationService,
                              WishListApplicationService wishListApplicationService,
                              UserApplicationService userApplicationService,
                              UserBookService userBookService) {
        this.bookApplicationService = bookApplicationService;
        this.wishListApplicationService = wishListApplicationService;
        this.userApplicationService = userApplicationService;
        this.userBookService = userBookService;
    }

    @Operation(summary = "Lists all books", description = "Lists all books from the wishlist.")
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<DisplayBookDto> listAllBooksInWishList(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return wishListApplicationService.listAllBooksInWishList(user.getUsername());
    }

    @Operation(summary = "Adds a book", description = "Adds a book to the wishlist.")
    @PostMapping("/add-book/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WishListDto> addBookToWishList(@RequestParam String bookName, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return wishListApplicationService.addBookInWishList(user.getUsername(), bookName)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Rents all books from wishlist", description = "Rents all books from the wishlist.")
    @PutMapping("/rent")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> rent(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            wishListApplicationService.rentAllBooksFromWishList(user.getUsername());
            return ResponseEntity.ok().build();
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
