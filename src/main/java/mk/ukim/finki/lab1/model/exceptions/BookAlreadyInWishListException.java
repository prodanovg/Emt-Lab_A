package mk.ukim.finki.lab1.model.exceptions;

public class BookAlreadyInWishListException extends RuntimeException {
    public BookAlreadyInWishListException(String id, String username) {
        super("Book with id " + id + "  and username " + username + "already in WishList");
    }
}
