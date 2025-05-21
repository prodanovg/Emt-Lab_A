package mk.ukim.finki.lab1.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum Role implements GrantedAuthority{
    ROLE_USER, ROLE_LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }

}
