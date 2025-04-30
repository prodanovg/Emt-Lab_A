package mk.ukim.finki.lab1.service.application.impl;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.dto.CreateUserDto;
import mk.ukim.finki.lab1.model.dto.DisplayUserDto;
import mk.ukim.finki.lab1.model.dto.LoginResponseDto;
import mk.ukim.finki.lab1.model.dto.LoginUserDto;
import mk.ukim.finki.lab1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.lab1.model.exceptions.InvalidgetAllUserNames;
import mk.ukim.finki.lab1.model.projections.UserProjection;
import mk.ukim.finki.lab1.service.application.UserApplicationService;
import mk.ukim.finki.lab1.service.domain.UserService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.lab1.helpers.JwtHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<UserProjection> getAllUserNames() {
        return Optional.ofNullable(userService.getAllUserNames()).orElseThrow(InvalidgetAllUserNames::new);
    }
}

