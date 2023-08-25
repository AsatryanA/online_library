package com.library.online_library.controller.order.admin;

import com.library.online_library.model.dto.order.DeliveryDateUpdateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderStatusUpdateRequest;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import com.library.online_library.model.dto.order.address.AddressDTO;
import com.library.online_library.service.order.admin.OrderAdminService;
import com.library.online_library.utils.pagination.CustomPageRequest;
import com.library.online_library.utils.pagination.sort.ImplSort;
import com.library.online_library.utils.pagination.sort.enums.OrderSortField;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/super/orders")
@PreAuthorize("hasRole(@role.ROLE_SUPER_ADMIN)")
public class OrderAdminController {

    private final OrderAdminService orderService;

    @PostMapping("/filter")
    public ResponseEntity<Page<OrderDTO>> getOrders(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(name = "sort", defaultValue = "ID") OrderSortField sort,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction) {
        var pageRequest = CustomPageRequest.from(page, size, ImplSort.by(sort.getName(), direction));
        return ResponseEntity.ok(orderService.getAll(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderWithItemsDTO> getOrderById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Long> updateOrderAddress(@RequestBody @Valid AddressDTO addressDTO, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.updateOrderAddress(addressDTO, id));
    }


    @PutMapping("/{id}/delivery-on")
    public ResponseEntity<Long> updateOrderDeliveryDate(@RequestBody @Valid DeliveryDateUpdateRequest deliveryDateUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.updateOrderDeliveryDate(deliveryDateUpdateRequest, id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Long> updateOrderStatus(@RequestBody @Valid OrderStatusUpdateRequest orderStatusUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderStatusUpdateRequest, id));
    }

}
