package com.library.online_library.model.dto.order_item;

import com.library.online_library.model.dto.book.BookDetails;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderItemDTO {

    private Integer count;

    private BigDecimal totalPrice;

    private BookDetails bookDetails;
}
