package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.WishList;
import mk.ukim.finki.lab1.model.dto.DisplayBookDto;
import mk.ukim.finki.lab1.model.dto.WishListDto;

import java.util.List;
import java.util.Optional;

public interface WishListApplicationService {
    List<DisplayBookDto> listAllBooksInWishList(String username);

    void rentAllBooksFromWishList(String username);

    Optional<WishListDto> addBookInWishList(String username, String bookName);

}
