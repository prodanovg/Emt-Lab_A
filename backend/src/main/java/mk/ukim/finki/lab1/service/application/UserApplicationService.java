package mk.ukim.finki.lab1.service.application;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.dto.CreateUserDto;
import mk.ukim.finki.lab1.model.dto.DisplayUserDto;
import mk.ukim.finki.lab1.model.dto.LoginResponseDto;
import mk.ukim.finki.lab1.model.dto.LoginUserDto;
import mk.ukim.finki.lab1.model.projections.UserProjection;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    List<UserProjection> getAllUserNames();

}
