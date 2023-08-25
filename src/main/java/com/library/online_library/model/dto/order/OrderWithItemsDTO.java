package com.library.online_library.model.dto.order;

import com.library.online_library.model.dto.order_item.OrderItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderWithItemsDTO extends OrderDTO {

    private List<OrderItemDTO> orderItems;

}
