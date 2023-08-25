package com.library.online_library.controller.order.user;

import com.library.online_library.model.dto.order.OrderCreateRequest;
import com.library.online_library.model.dto.order.OrderDTO;
import com.library.online_library.model.dto.order.OrderWithItemsDTO;
import com.library.online_library.service.order.user.OrderService;
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
@RequestMapping(API_V1 + "/orders")
public class OrderUserController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole(@role.ROLE_USER)")
    public ResponseEntity<Long> create(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        return ResponseEntity.ok(orderService.create(orderCreateRequest));
    }

    @GetMapping
    @PreAuthorize("hasRole(@role.ROLE_USER)")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(name = "sort", defaultValue = "ID") OrderSortField sort,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction,
            @RequestParam boolean isActive) {
        var pageRequest = CustomPageRequest.from(page, size, ImplSort.by(sort.getName(), direction));
        return ResponseEntity.ok(orderService.getAllOrders(isActive, pageRequest));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole(@role.ROLE_USER)")
    public ResponseEntity<OrderWithItemsDTO> getOrderById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}