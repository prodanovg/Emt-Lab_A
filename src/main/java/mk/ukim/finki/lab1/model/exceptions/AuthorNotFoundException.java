package mk.ukim.finki.lab1.model.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("No author found exception");
    }
}
