package com.library.online_library.service.order.admin.impl;

import com.library.online_library.mapper.order.OrderMapper;
import com.library.online_library.model.dto.order.DeliveryDateUpdateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderStatusUpdateRequest;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import com.library.online_library.model.dto.order.address.AddressDTO;
import com.library.online_library.model.entity.order.OrderEntity;
import com.library.online_library.repository.order.OrderRepository;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.service.order.admin.OrderAdminService;
import com.library.online_library.utils.constants.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OrderAdminServiceImpl implements OrderAdminService {

    private final FindOne findOne;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> getAll(PageRequest pageRequest) {
        var orderEntities = orderRepository.findAll(pageRequest);
        return orderEntities.map(orderMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderWithItemsDTO getOrderById(Long orderId) {
        var orderEntity = findOne.getOrderEntity(orderId);
        return orderMapper.toOrderWithItemsDTO(orderEntity);
    }

    @Override
    @Transactional
    public Long updateOrderAddress(AddressDTO addressDTO, Long orderId) {
        var orderEntity = findOne.getOrderEntity(orderId);
        setAddressData(orderEntity, addressDTO);
        orderRepository.save(orderEntity);
        return orderEntity.getId();
    }

    @Override
    @Transactional
    public Long updateOrderDeliveryDate(DeliveryDateUpdateRequest deliveryDateUpdateRequest, Long orderId) {
        var orderEntity = findOne.getOrderEntity(orderId);
        orderEntity.setDeliverOn(deliveryDateUpdateRequest.getDeliverOn());
        orderRepository.save(orderEntity);
        return orderEntity.getId();
    }

    @Override
    @Transactional
    public Long updateOrderStatus(OrderStatusUpdateRequest orderStatusUpdateRequest, Long orderId) {
        var orderEntity = findOne.getOrderEntity(orderId);
        var orderStatus = orderStatusUpdateRequest.getOrderStatus();
        orderEntity.setStatus(orderStatus);
        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            orderEntity.setDeliveredOn(LocalDateTime.now());
        }
        orderRepository.save(orderEntity);
        return orderEntity.getId();
    }

    private void setAddressData(OrderEntity orderEntity, AddressDTO address) {
        if (nonNull(address)) {
            orderEntity.setAddressDetails(address.getAddressDetails());
            orderEntity.setFullAddress(address.getFullAddress());
            orderEntity.setLongitude(address.getLongitude());
            orderEntity.setLatitude(address.getLatitude());
        }
    }
}
