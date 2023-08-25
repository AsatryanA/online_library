package com.library.online_library.model.dto.order;

import com.library.online_library.utils.constants.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderStatusUpdateRequest {
    private OrderStatus orderStatus;
}
