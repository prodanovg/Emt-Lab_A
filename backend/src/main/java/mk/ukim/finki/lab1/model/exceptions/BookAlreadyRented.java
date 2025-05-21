package mk.ukim.finki.lab1.model.exceptions;

public class BookAlreadyRented extends RuntimeException {
    public BookAlreadyRented() {
        super("Book already rented");
    }
}
