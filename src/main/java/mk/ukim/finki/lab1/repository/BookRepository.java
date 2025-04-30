package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.domain.Book;
import mk.ukim.finki.lab1.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory(Category category);

    //1
    List<Book> findAllByNameAndAuthorIdAndDescription(String name, Long authorId, String description);
    //2
    List<Book> findAllByAuthorIdOrCategory(Long authorId, Category category);

    Book findByName(String name);
 }
