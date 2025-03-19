package mk.ukim.finki.lab1.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("No book found exception");
    }
}
