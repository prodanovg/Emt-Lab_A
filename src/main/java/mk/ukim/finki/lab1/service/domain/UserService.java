package mk.ukim.finki.lab1.service.domain;

import mk.ukim.finki.lab1.model.enumerations.Role;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.projections.UserProjection;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    List<UserProjection> getAllUserNames();

}
