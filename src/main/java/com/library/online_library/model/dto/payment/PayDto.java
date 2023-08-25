package com.library.online_library.model.dto.payment;

import com.library.online_library.model.dto.order.OrderCreateRequest;
import com.library.online_library.model.entity.order.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PayDto {

    private OrderEntity orderEntity;
    private OrderCreateRequest orderCreateRequest;

}
