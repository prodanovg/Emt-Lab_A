package mk.ukim.finki.lab1.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    public UserBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }
    public UserBook(Long id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }


}
