package com.library.online_library.model.dto.basket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketItemCreateRequest {

    @NotNull
    @Positive
    private Integer count;
    @NotNull
    @Positive
    private Long bookId;
}
