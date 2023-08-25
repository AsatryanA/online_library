package com.library.online_library.controller.author.user;

import com.library.online_library.model.dto.author.AuthorDTO;
import com.library.online_library.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.library.online_library.utils.constants.AppConstants.API_V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/authors")
public class AuthorUserController {

    private final AuthorService authorService;

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }
}
