package com.library.online_library.model.dto.card;

import com.library.online_library.utils.constants.CardType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseCardDto {

    private CardType cardType;
    @NotBlank
    private String cardNumber;

    @NotBlank
    private String expireMonth;

    @NotBlank
    private String expireYear;

    @NotBlank
    private String cvv;

}
