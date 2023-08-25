package com.library.online_library.model.dto.payment;

import com.library.online_library.utils.constants.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequestDto {

    @NotNull
    private PaymentType paymentType;

}
