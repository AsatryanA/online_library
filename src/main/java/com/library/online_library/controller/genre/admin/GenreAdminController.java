package com.library.online_library.controller.genre.admin;

import com.library.online_library.model.dto.genre.GenreCreateRequest;
import com.library.online_library.model.dto.genre.GenreDTO;
import com.library.online_library.model.dto.genre.GenreUpdateRequest;
import com.library.online_library.service.genre.GenreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/admin/genres")
@PreAuthorize("hasRole(@role.ROLE_ADMIN)")
public class GenreAdminController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody @Valid GenreCreateRequest genreCreateRequest) {
        return ResponseEntity.ok(genreService.create(genreCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable @Positive Long id,
                                           @RequestBody @Valid GenreUpdateRequest genreUpdateRequest) {
        return ResponseEntity.ok(genreService.update(id, genreUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        genreService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }
}