package com.library.online_library.model.dto.basket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketItemUpdateRequest {
    @NotNull
    @Positive
    private Long bookId;

    @NotNull
    @Min(0)
    private Integer count;
}
