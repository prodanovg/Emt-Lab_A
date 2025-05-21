package mk.ukim.finki.lab1.listeners;


import mk.ukim.finki.lab1.events.AuthorChangedEvent;
import mk.ukim.finki.lab1.events.AuthorCreatedEvent;
import mk.ukim.finki.lab1.events.AuthorDeletedEvent;
import mk.ukim.finki.lab1.service.application.CountryApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {
    private final CountryApplicationService countryApplicationService;

    public AuthorEventHandlers(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }
    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}
