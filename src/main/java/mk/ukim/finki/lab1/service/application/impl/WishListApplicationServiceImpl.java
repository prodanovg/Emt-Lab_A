package mk.ukim.finki.lab1.service.application.impl;

import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.WishList;
import mk.ukim.finki.lab1.model.dto.DisplayBookDto;
import mk.ukim.finki.lab1.model.dto.WishListDto;
import mk.ukim.finki.lab1.service.application.WishListApplicationService;
import mk.ukim.finki.lab1.service.domain.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListApplicationServiceImpl implements WishListApplicationService {

    private final WishListService wishListService;

    public WishListApplicationServiceImpl(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishList(String username) {
        return DisplayBookDto.from(wishListService.listAllBooksInWishList(username));
    }

    @Override
    public void rentAllBooksFromWishList(String username) {
        wishListService.rentAllBooksFromWishList(username);
    }

    @Override
    public Optional<WishListDto> addBookInWishList(String username, String bookName) {
        return wishListService.addBookInWishList(username,bookName).map(WishListDto::from);
    }
}
