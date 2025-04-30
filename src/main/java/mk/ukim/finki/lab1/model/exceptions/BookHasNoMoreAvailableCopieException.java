package mk.ukim.finki.lab1.model.exceptions;

public class BookHasNoMoreAvailableCopieException extends RuntimeException {
    public BookHasNoMoreAvailableCopieException(String name) {
        super("Book with name " + name + " has no more available copies");
    }
}
