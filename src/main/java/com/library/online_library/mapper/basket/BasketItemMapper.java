package com.library.online_library.mapper.basket;

import com.library.online_library.model.dto.basket.BaseBasketItemDTO;
import com.library.online_library.model.dto.basket.BasketItemDTO;
import com.library.online_library.model.entity.basket.BasketItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BasketItemMapper {
    @Mapping(target = "bookDTO", source = "book")
    BaseBasketItemDTO toBaseBasketItemDto(BasketItemEntity basketItemEntity);


    BasketItemDTO toBasketItemDto(BasketItemEntity basketItemEntity);

}
