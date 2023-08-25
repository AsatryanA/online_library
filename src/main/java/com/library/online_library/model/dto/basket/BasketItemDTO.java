package com.library.online_library.model.dto.basket;

import com.library.online_library.model.dto.book.BookDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketItemDTO extends BaseBasketItemDTO {

    private BookDTO bookDTO;

}
