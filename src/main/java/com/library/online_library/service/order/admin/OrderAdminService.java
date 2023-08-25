package com.library.online_library.service.order.admin;

import com.library.online_library.model.dto.order.DeliveryDateUpdateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderStatusUpdateRequest;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import com.library.online_library.model.dto.order.address.AddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface OrderAdminService {

    Page<OrderDTO> getAll(PageRequest pageRequest);

    OrderWithItemsDTO getOrderById(Long orderId);

    Long updateOrderAddress(AddressDTO addressDTO, Long orderId);

    Long updateOrderDeliveryDate(DeliveryDateUpdateRequest deliveryDateUpdateRequest, Long orderId);

    Long updateOrderStatus(OrderStatusUpdateRequest orderStatusUpdateRequest, Long orderId);
}
