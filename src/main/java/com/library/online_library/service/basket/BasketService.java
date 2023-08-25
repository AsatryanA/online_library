package com.library.online_library.service.basket;


import com.library.online_library.model.dto.basket.BaseBasketItemDTO;
import com.library.online_library.model.dto.basket.BasketDTO;
import com.library.online_library.model.dto.basket.BasketItemCreateRequest;
import com.library.online_library.model.dto.basket.BasketItemUpdateRequest;
import com.library.online_library.model.entity.basket.BasketItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BasketService {

    BaseBasketItemDTO addToBasket(BasketItemCreateRequest createRequest);

    BaseBasketItemDTO updateCount(BasketItemUpdateRequest updateRequest);

    void deleteByBookId(Long bookId);

    BasketDTO getBasket();

    List<BasketItemEntity> getAllBasketItems();

    void deleteAll();
}
