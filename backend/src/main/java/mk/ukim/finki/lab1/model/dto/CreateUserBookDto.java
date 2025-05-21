package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public record CreateUserBookDto(
        Long bookId,
        String username
) {
    public static CreateUserBookDto from(UserBook userBook) {
        return new CreateUserBookDto(
                userBook.getBook().getId(),
                userBook.getUser().getUsername()
        );
    }

    public static List<CreateUserBookDto> from(List<UserBook> userBooks) {
        return userBooks.stream().map(CreateUserBookDto::from).collect(Collectors.toList());

    }

    public UserBook toUserBook(User user, Book book) {
        return new UserBook(user, book);
    }
}
