package mk.ukim.finki.lab1.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.lab1.model.dto.CreateAuthorDto;
import mk.ukim.finki.lab1.model.dto.CreateCountryDto;
import mk.ukim.finki.lab1.model.dto.DisplayAuthorDto;
import mk.ukim.finki.lab1.model.dto.DisplayCountryDto;
import mk.ukim.finki.lab1.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all available countries.")
    @GetMapping
    public ResponseEntity<List<DisplayCountryDto>> findAll() {
        List<DisplayCountryDto> countries = countryApplicationService.findAll();
        return ResponseEntity.ok(countries);
    }

    @Operation(summary = "Get country by ID", description = "Finds an country by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new country", description = "Creates a new country based on the given CountryDto.")
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.save(createCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing country", description = "Updates an author by ID using CountryDto.")
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.update(id, createCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an country", description = "Deletes an country by their ID.")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countryApplicationService.findById(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
