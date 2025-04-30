package mk.ukim.finki.lab1.service.domain.impl;

import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.WishList;
import mk.ukim.finki.lab1.model.exceptions.*;
import mk.ukim.finki.lab1.repository.WishListRepository;
import mk.ukim.finki.lab1.service.domain.BookService;
import mk.ukim.finki.lab1.service.domain.UserService;
import mk.ukim.finki.lab1.service.domain.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {

    private final UserService userService;
    private final BookService bookService;
    private final WishListRepository wishListRepository;

    public WishListServiceImpl(UserService userService, BookService bookService, WishListRepository wishListRepository) {
        this.userService = userService;
        this.bookService = bookService;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public List<Book> listAllBooksInWishList(String username) {
        if (wishListRepository.findByUserUsername(username) == null) {
            throw new WishListNotFoundException(username);
        }
        return wishListRepository.findByUserUsername(username).getBooks();
    }

    @Override
    public void rentAllBooksFromWishList(String username) {
        User user = userService.findByUsername(username);
        WishList wishList = user.getWishList();

        if(wishList.getBooks().isEmpty()) {
            throw new EmptyWishListException(username);
        }

        for(Book book : wishList.getBooks()) {
            if(book.getAvailableCopies() > 0){
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookService.save(book);
            }
            else{
                throw new BookHasNoMoreAvailableCopieException(book.getName());
            }
        }

        wishList.getBooks().clear();
        wishListRepository.save(wishList);


    }

    @Override
    public Optional<WishList> addBookInWishList(String username, String bookName) {
        User user = userService.findByUsername(username);
        WishList wishList = user.getWishList();
        Book book = bookService.findByName(bookName);

        if (book.getAvailableCopies() > 0) {
            if (!wishList.getBooks().stream().filter(i -> i.getId().equals(bookName)).toList().isEmpty())
                throw new BookAlreadyInWishListException(bookName, username);
            wishList.getBooks().add(book);
            return Optional.of(wishListRepository.save(wishList));
        } else {
            throw new BookHasNoMoreAvailableCopieException(book.getName());
        }
    }
}
