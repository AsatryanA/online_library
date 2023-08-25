package com.library.online_library.model.entity.refresh_token;

import com.library.online_library.model.entity.user.UserEntity;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Getter
@Setter
@Entity
@Table(name = EntityName.REFRESH_TOKEN)
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity user;

    @PrePersist
    public void prePersist() {
        if (nonNull(this.createdAt)) {
            return;
        }
        var now = LocalDateTime.now();
        setCreatedAt(now);
    }
}
