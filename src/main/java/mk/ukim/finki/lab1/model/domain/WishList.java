package mk.ukim.finki.lab1.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    List<Book> books = new ArrayList<>();

    public WishList(User user, List<Book> books) {
        this.user = user;
        this.books = new ArrayList<>(books);
    }

    public WishList(User user, List<Book> books, Long id) {
        this.user = user;
        this.books = new ArrayList<>(books);
        this.id = id;
    }
}
