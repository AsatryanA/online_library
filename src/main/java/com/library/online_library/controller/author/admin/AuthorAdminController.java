package com.library.online_library.controller.author.admin;

import com.library.online_library.model.dto.author.AuthorCreateRequest;
import com.library.online_library.model.dto.author.AuthorDTO;
import com.library.online_library.model.dto.author.AuthorUpdateRequest;
import com.library.online_library.service.author.AuthorService;
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
@RequestMapping(API_V1 + "/admin/authors")
@PreAuthorize("hasRole(@role.ROLE_ADMIN)")
public class AuthorAdminController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCreateRequest authorCreateRequest) {
        return ResponseEntity.ok(authorService.create(authorCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable @Positive Long id,
                                           @RequestBody @Valid AuthorUpdateRequest authorUpdateRequest) {
        return ResponseEntity.ok(authorService.update(id, authorUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        authorService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }



}
