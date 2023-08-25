package com.library.online_library.model.dto.order.address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {

    @NotBlank
    private String fullAddress;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @Valid
    private AddressDetails addressDetails;

}
