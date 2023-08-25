package com.library.online_library.model.dto.order;

import com.library.online_library.model.dto.payment.PaymentRequestDto;
import com.library.online_library.utils.constants.DeliverOnStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderCreateRequest extends BaseOrderDTO {

    @NotNull
    private DeliverOnStatus deliverOnStatus;

    @NotNull
    @Valid
    private PaymentRequestDto paymentRequest;
}
