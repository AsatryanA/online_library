package com.library.online_library.model.dto.order.address;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AddressDetails implements Serializable {

    private String entrance;
    private String floor;
    private String aptNumber;
    private String intercom;

}
