package mk.ukim.finki.lab1.service.domain.impl;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.enumerations.Role;
import mk.ukim.finki.lab1.model.exceptions.*;
import mk.ukim.finki.lab1.model.projections.UserProjection;
import mk.ukim.finki.lab1.repository.UserRepository;
import mk.ukim.finki.lab1.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }


    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidUserCredentialsException();
        return user;

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public List<UserProjection> getAllUserNames() {
        return userRepository.findAllProjectedBy();
    }

}
