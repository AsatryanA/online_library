package com.library.online_library.service.order.user.impl;


import com.library.online_library.mapper.order.OrderMapper;
import com.library.online_library.mapper.order_item.OrderItemMapper;
import com.library.online_library.model.dto.order.OrderCreateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import com.library.online_library.model.entity.order_item.OrderItemEntity;
import com.library.online_library.repository.order.OrderRepository;
import com.library.online_library.service.basket.BasketService;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.service.order.user.OrderService;
import com.library.online_library.specification.OrderSpecification;
import com.library.online_library.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderUserServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final BasketService basketService;
    private final FindOne findOne;

    @Override
    @Transactional
    public Long create(OrderCreateRequest orderCreateRequest) {
        var basketItemEntities = basketService.getAllBasketItems();
        var basket = basketService.getBasket();
        List<OrderItemEntity> orderItems = basketItemEntities
                .stream()
                .map(orderItemMapper::fromBasketItem)
                .toList();
        var orderEntity = orderMapper.toEntity(orderCreateRequest, basket);
        orderEntity.setOrderItems(orderItems);
        orderRepository.save(orderEntity);
        basketService.deleteAll();
        return orderEntity.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> getAllOrders(boolean isActive, PageRequest pageRequest) {
        return orderRepository.findAllByStatusAndUser_Id(isActive, CurrentUser.getId(), pageRequest).map(orderMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderWithItemsDTO getOrderById(Long orderId) {
        var orderEntity = findOne.getOrderEntity(orderId);
        return orderMapper.toOrderWithItemsDTO(orderEntity);
    }
}
