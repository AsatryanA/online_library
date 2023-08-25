package com.library.online_library.controller.card;

import com.library.online_library.model.dto.book.BookDTO;
import com.library.online_library.model.dto.book.BookUpdateRequest;
import com.library.online_library.model.dto.card.CardCreateRequest;
import com.library.online_library.model.dto.card.CardDTO;
import com.library.online_library.model.dto.card.CardUpdateRequest;
import com.library.online_library.service.card.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@RestController
@RequestMapping(API_V1 + "/cards")
@RequiredArgsConstructor
@PreAuthorize("hasRole(@role.ROLE_USER)")
public class CardController {

    private final CardService cardService;

    @PostMapping

    public ResponseEntity<CardDTO> create(@RequestBody @Valid CardCreateRequest cardCreateRequest) {
        return ResponseEntity.ok(cardService.create(cardCreateRequest));
    }
    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll() {
        return ResponseEntity.ok(cardService.getAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        cardService.delete(id);
    }

}
