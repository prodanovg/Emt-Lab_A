package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.dto.CreateBookDto;
import mk.ukim.finki.lab1.model.dto.DisplayBookDto;
import mk.ukim.finki.lab1.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    List<DisplayBookDto> findAll();

    Optional<DisplayBookDto> save(CreateBookDto createBookDto);

    Optional<DisplayBookDto> findById(Long id);

    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);

//    Optional<DisplayBookDto> rent(Long id);

    List<DisplayBookDto> findByCategory(Category category);

    List<DisplayBookDto> findByNameAndAuthorAndDescription(String name, Long authorId, String description);

    List<DisplayBookDto> findRelatedAuthorOrCategory(Long authorId, Category category);

    void deleteById(Long id);

    DisplayBookDto findByName(String name);
}

