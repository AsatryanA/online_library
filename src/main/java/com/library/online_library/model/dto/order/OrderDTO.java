package com.library.online_library.model.dto.order;

import com.library.online_library.utils.constants.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO extends BaseOrderDTO{

    private Long id;

    private Integer totalCount;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private LocalDateTime deliveredOn;

    private LocalDateTime createdAt;
}
