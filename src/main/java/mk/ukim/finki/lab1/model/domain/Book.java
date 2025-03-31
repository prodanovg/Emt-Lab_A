package mk.ukim.finki.lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.lab1.model.enumerations.Category;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private int availableCopies;

    private Boolean rented ;
    //4
    private String rentedBy;

    //5
    private Boolean deleted = false;

    public Book(String name, Category category, Author author, int availableCopies, String description, Boolean rented) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.rented = false;
        this.description = description;

    }

}