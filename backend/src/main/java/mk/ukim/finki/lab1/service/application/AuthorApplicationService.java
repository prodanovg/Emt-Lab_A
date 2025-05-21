package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.domain.Author;
import mk.ukim.finki.lab1.model.dto.CreateAuthorDto;
import mk.ukim.finki.lab1.model.dto.DisplayAuthorDto;
import mk.ukim.finki.lab1.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDtoDto);

    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDtoDto);

    void deleteById(Long id);

    void refreshMaterializedView();

    List<BooksPerAuthorView> findAllBooksPerAuthor();

    BooksPerAuthorView findBooksPerAuthor(Long id);


}
