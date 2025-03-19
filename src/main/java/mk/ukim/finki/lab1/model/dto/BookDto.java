package mk.ukim.finki.lab1.model.dto;

import lombok.Data;
import mk.ukim.finki.lab1.model.enumerations.Category;

@Data
public class BookDto {
    private String name;

    private Category category;

    private Long author;

    private int availableCopies;

    private Boolean rented = false ;

    private String description;

    private String rentedBy;

    public BookDto() {

    }

    public BookDto(String name, Category category, Long author, int availableCopies, Boolean rented, String description,String rentedBy) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.rented = rented;
        this.description = description;
        this.rentedBy = rentedBy;
    }


}
