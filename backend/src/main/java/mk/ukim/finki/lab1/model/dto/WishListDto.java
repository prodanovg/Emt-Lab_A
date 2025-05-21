package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.WishList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record WishListDto(
        long id,
        DisplayUserDto user,
        List<DisplayBookDto> books
) {
    public static WishListDto from(WishList wishList) {
        return new WishListDto(
                wishList.getId(),
                DisplayUserDto.from(wishList.getUser()),
                DisplayBookDto.from(wishList.getBooks())
        );
    }

    public static List<WishListDto> from(List<WishList> wishList) {
        return wishList.stream().map(WishListDto::from).collect(Collectors.toList());
    }
}
