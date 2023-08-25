package com.library.online_library.model.dto.order;

import com.library.online_library.model.dto.order.address.AddressDTO;
import com.library.online_library.utils.constants.DeliverOnStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseOrderDTO {

    private ReceiverDTO receiver;

    private LocalDateTime deliverOn;

    @NotNull
    private DeliverOnStatus deliverOnStatus;

    @Valid
    private AddressDTO address;
}
