package com.library.online_library.model.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DeliveryDateUpdateRequest {
    @NotNull
    private LocalDateTime deliverOn;
}
