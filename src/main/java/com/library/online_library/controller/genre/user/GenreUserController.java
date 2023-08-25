package com.library.online_library.controller.genre.user;

import com.library.online_library.model.dto.genre.GenreDTO;
import com.library.online_library.service.genre.GenreService;
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
@RequestMapping(API_V1 + "/genres")
public class GenreUserController {

    private final GenreService genreService;

    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }

}