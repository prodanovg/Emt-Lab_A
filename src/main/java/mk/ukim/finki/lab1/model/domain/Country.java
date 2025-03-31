package mk.ukim.finki.lab1.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;

    public Country(String continent, String name) {
        this.continent = continent;
        this.name = name;
    }
    public Country(Long id , String continent, String name) {
        this.id = id;
        this.continent = continent;
        this.name = name;
    }

}
