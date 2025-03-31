package mk.ukim.finki.lab1.model.dto;

public record LoginUserDto(String username, String password) {
    public boolean isValid() {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}
