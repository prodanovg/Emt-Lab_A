package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(
        String name,
        String surname,
        Long countryId
) {
    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).collect(Collectors.toList());

    }

    public Author toAuthor(Long id,Country country) {
        if (country.getId().equals(countryId)) {
            return new Author(id,name, surname, country);
        } else {
            throw new IllegalArgumentException("Country id does not match");
        }
    }


}
