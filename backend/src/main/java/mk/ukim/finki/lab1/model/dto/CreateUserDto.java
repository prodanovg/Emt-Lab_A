package mk.ukim.finki.lab1.model.dto;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }

}
