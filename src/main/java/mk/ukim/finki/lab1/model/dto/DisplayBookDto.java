package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        String name,
        String description,
        Category category,
        Long authorId,
        int availableCopies,
        Boolean rented,
        String rentedBy,
        Boolean deleted
) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getName(),
                book.getDescription(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies(),
                book.getRented(),
                book.getRentedBy(),
                book.getDeleted()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Long id, Author author) {
        if (author.getId().equals(authorId)) {
            return new Book(id, name, description, category, author, availableCopies, rented);
        } else {
            throw new IllegalArgumentException("Author id does not match");
        }
    }
}
