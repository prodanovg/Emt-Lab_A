package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDto(
        String name,
        String surname,
        Long countryId
) {
    public static CreateAuthorDto from(Author author) {
        return new CreateAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<CreateAuthorDto> from(List<Author> authors) {
        return authors.stream().map(CreateAuthorDto::from).collect(Collectors.toList());

    }

    public Author toAuthor(Country country) {
        if (country.getId().equals(countryId)) {
            return new Author(name, surname, country);
        } else {
            throw new IllegalArgumentException("Country id does not match");
        }
    }

}
