package com.library.online_library.model.entity.order;

import com.library.online_library.model.dto.order.address.AddressDetails;
import com.library.online_library.model.entity.BaseEntity;
import com.library.online_library.model.entity.order_item.OrderItemEntity;
import com.library.online_library.model.entity.user.UserEntity;
import com.library.online_library.utils.constants.DeliverOnStatus;
import com.library.online_library.utils.constants.EntityName;
import com.library.online_library.utils.constants.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = EntityName.ORDER)
public class OrderEntity extends BaseEntity {

    @Column
    private String receiverName;

    @Column
    private String receiverPhoneNumber;

    @Column
    private String fullAddress;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private String note;

    //count of order_items
    @Column(nullable = false)
    private Integer totalCount;

    //should contain product prices for all products and city delivery price
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private BigDecimal deliveryPrice;


    @Column
    private LocalDateTime deliverOn;

    @Column
    private LocalDateTime deliveredOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliverOnStatus deliverOnStatus;

    @JdbcTypeCode(SqlTypes.JSON)
    private AddressDetails addressDetails;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemEntity> orderItems;


}
