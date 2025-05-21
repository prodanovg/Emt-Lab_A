package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.domain.Country;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.UserBook;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserBookDto(
        Long id,
        String bookName,
        String username,
        String authorName
) {
    public static DisplayUserBookDto from(UserBook userBook) {
        return new DisplayUserBookDto(
                userBook.getId(),
                userBook.getBook().getName(),
                userBook.getUser().getUsername(),
                userBook.getBook().getAuthor().getName()
        );
    }

    public static List<DisplayUserBookDto> from(List<UserBook> userBooks) {
        return userBooks.stream().map(DisplayUserBookDto::from).collect(Collectors.toList());
    }

    public UserBook toUserBook(Book book, User user) {
        return new UserBook(user, book);
    }
}
