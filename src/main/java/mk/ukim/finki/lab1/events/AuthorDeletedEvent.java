package mk.ukim.finki.lab1.events;

import lombok.Getter;
import mk.ukim.finki.lab1.model.domain.Author;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorDeletedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorDeletedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}
