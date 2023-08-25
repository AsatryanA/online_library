package com.library.online_library.model.entity.order_item;

import com.library.online_library.model.dto.book.BookDetails;
import com.library.online_library.model.entity.BaseEntity;
import com.library.online_library.model.entity.book.BookEntity;
import com.library.online_library.model.entity.order.OrderEntity;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = EntityName.ORDER_ITEM)
public class OrderItemEntity extends BaseEntity {

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private BookDetails bookDetails;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderEntity order;
}
