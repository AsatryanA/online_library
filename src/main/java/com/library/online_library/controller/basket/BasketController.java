package com.library.online_library.controller.basket;

import com.library.online_library.model.dto.basket.BaseBasketItemDTO;
import com.library.online_library.model.dto.basket.BasketDTO;
import com.library.online_library.model.dto.basket.BasketItemCreateRequest;
import com.library.online_library.model.dto.basket.BasketItemUpdateRequest;
import com.library.online_library.service.basket.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@RestController
@RequestMapping(API_V1 + "/basket")
@RequiredArgsConstructor
@PreAuthorize("hasRole(@role.ROLE_USER)")
public class BasketController {

    private final BasketService basketService;

    @PostMapping
    public ResponseEntity<BaseBasketItemDTO> addToBasket(@RequestBody @Valid BasketItemCreateRequest createRequest) {
        return ResponseEntity.ok(basketService.addToBasket(createRequest));
    }

    @PutMapping
    public ResponseEntity<BaseBasketItemDTO> updateCount(@RequestBody @Valid BasketItemUpdateRequest updateRequest) {
        return ResponseEntity.ok(basketService.updateCount(updateRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        basketService.deleteByBookId(id);
    }

    @GetMapping
    public ResponseEntity<BasketDTO> getBasket() {
        return ResponseEntity.ok(basketService.getBasket());
    }
}
