package mk.ukim.finki.lab1.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password");
    }
}
