package com.library.online_library.mapper.order_item;

import com.library.online_library.model.entity.basket.BasketItemEntity;
import com.library.online_library.model.entity.order_item.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemEntity fromBasketItem(BasketItemEntity basketItemDTO);
}
