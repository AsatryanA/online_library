package com.library.online_library.model.entity.card;

import com.library.online_library.model.entity.BaseEntity;
import com.library.online_library.model.entity.user.UserEntity;
import com.library.online_library.utils.constants.CardType;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = EntityName.CARD)
public class CardEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String expireMonth;
    @Column(nullable = false)
    private String expireYear;

    @Column(nullable = false)
    private String cvv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity user;

}
