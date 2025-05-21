package mk.ukim.finki.lab1.model.exceptions;

public class EmptyWishListException extends RuntimeException {
    public EmptyWishListException(String username) {
        super("WishList for user " + username + " is empty");
    }
}
