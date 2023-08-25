package com.library.online_library.mapper.order;

import com.library.online_library.model.dto.basket.BasketDTO;
import com.library.online_library.model.dto.order.OrderCreateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import com.library.online_library.model.entity.order.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity toEntity(OrderCreateRequest orderCreateRequest);

    default OrderEntity toEntity(OrderCreateRequest orderCreateRequest, BasketDTO basket) {
        OrderEntity orderEntity = toEntity(orderCreateRequest);
        orderEntity.setTotalAmount(basket.getTotalPrice());
        return orderEntity;
    }

    OrderDTO toDTO(OrderEntity orderEntity);

    OrderWithItemsDTO toOrderWithItemsDTO(OrderEntity orderEntity);
}
