package com.library.online_library.model.dto.basket;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class BasketDTO {
    private BigDecimal totalPrice;
    private List<BasketItemDTO> basketItems;
}
