package com.library.online_library.model.entity.basket;

import com.library.online_library.model.entity.book.BookEntity;
import com.library.online_library.model.entity.user.UserEntity;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = EntityName.BASKET_ITEM)
public class BasketItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BookEntity book;

}
