package com.library.online_library.service.order.user;

import com.library.online_library.model.dto.order.OrderCreateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface OrderService {

    Long create(OrderCreateRequest orderCreateRequest);

    Page<OrderDTO> getAllOrders(boolean isActive, PageRequest pageRequest);

    OrderWithItemsDTO getOrderById(Long orderId);

}
