package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.dto.CreateUserDto;
import mk.ukim.finki.lab1.model.dto.DisplayUserDto;
import mk.ukim.finki.lab1.model.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
