package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(
        String name,
        String description,
        Category category,
        Long authorId,
        int availableCopies
) {
    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getName(),
                book.getDescription(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        if (author.getId().equals(authorId)) {
            return new Book(name, description, category, author, availableCopies);
        }
        else {
            throw new IllegalArgumentException("Author id does not match");
        }
    }
}
