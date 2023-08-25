package com.library.online_library.service.basket.impl;

import com.library.online_library.exeption.ResourceNotFoundException;
import com.library.online_library.mapper.basket.BasketItemMapper;
import com.library.online_library.model.dto.basket.BaseBasketItemDTO;
import com.library.online_library.model.dto.basket.BasketDTO;
import com.library.online_library.model.dto.basket.BasketItemCreateRequest;
import com.library.online_library.model.dto.basket.BasketItemUpdateRequest;
import com.library.online_library.model.entity.basket.BasketItemEntity;
import com.library.online_library.repository.basket.BasketItemRepository;
import com.library.online_library.service.basket.BasketService;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.utils.CurrentUser;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final FindOne findOne;
    private final BasketItemMapper basketItemMapper;
    private final BasketItemRepository basketItemRepository;

    @Override
    @Transactional
    public BaseBasketItemDTO addToBasket(BasketItemCreateRequest createRequest) {
        var userId = CurrentUser.getId();
        var bookId = createRequest.getBookId();
        if(basketItemRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new EntityExistsException("This book already has added update count");
        }
        var basketItemEntity = new BasketItemEntity();
        basketItemEntity.setCount(createRequest.getCount());
        basketItemEntity.setUser(findOne.getUserById(userId));
        basketItemEntity.setBook(findOne.getBookEntity(bookId));
        basketItemRepository.save(basketItemEntity);
        return basketItemMapper.toBaseBasketItemDto(basketItemEntity);
    }

    @Override
    @Transactional
    public BaseBasketItemDTO updateCount(BasketItemUpdateRequest updateRequest) {
        var userId = CurrentUser.getId();
        var bookId = updateRequest.getBookId();
        var basketItemEntity = basketItemRepository.findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.BASKET_ITEM, "book_id", bookId.toString()));
        var count = updateRequest.getCount();
        basketItemEntity.setCount(count);
        var baseBasketItemDTO = basketItemMapper.toBaseBasketItemDto(basketItemEntity);
        if(count == 0) {
            basketItemRepository.delete(basketItemEntity);
            return baseBasketItemDTO;
        }
        basketItemRepository.save(basketItemEntity);
        return basketItemMapper.toBaseBasketItemDto(basketItemEntity);
    }

    @Override
    @Transactional
    public void deleteByBookId(Long bookId) {
        basketItemRepository.deleteByUserIdAndBookId(CurrentUser.getId(), bookId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        basketItemRepository.deleteAllByUserId(CurrentUser.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public BasketDTO getBasket() {
        var basketItemEntities = getAllBasketItems();
        return getBasketDto(basketItemEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasketItemEntity> getAllBasketItems() {
        return basketItemRepository.findAllByUserId(CurrentUser.getId());
    }

    private BasketDTO getBasketDto(List<BasketItemEntity> basketItemEntities) {
        var basketDTO = new BasketDTO();
        var basketItemDTOS = basketItemEntities.stream().map(basketItemMapper::toBasketItemDto).toList();
        basketDTO.setBasketItems(basketItemDTOS);
        var productTotalPrice = getBooksTotalPrice(basketItemEntities);
        basketDTO.setTotalPrice(productTotalPrice);
        return basketDTO;
    }

    private BigDecimal getBooksTotalPrice(List<BasketItemEntity> basketItemEntities) {
        return basketItemEntities
                .stream()
                .map(basketItemEntity -> {
                    var count = basketItemEntity.getCount();
                    return basketItemEntity.getBook().getPrice().multiply(BigDecimal.valueOf(count));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

