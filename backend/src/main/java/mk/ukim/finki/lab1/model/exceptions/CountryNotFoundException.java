package mk.ukim.finki.lab1.model.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException() {
        super("No country found exception");
    }
}
