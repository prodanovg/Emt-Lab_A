package mk.ukim.finki.lab1.model.exceptions;

public class WishListNotFoundException extends RuntimeException {
    public WishListNotFoundException(String id) {
        super("WishList with id " + id + " not found");
    }
}
