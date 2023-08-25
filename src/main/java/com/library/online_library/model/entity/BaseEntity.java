package com.library.online_library.model.entity;

import com.library.online_library.service.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private Long createdBy;

    @Column
    private Long lastModifiedBy;

    @PrePersist
    public void prePersist() {
        if (nonNull(this.createdAt) && nonNull(updatedAt)) {
            return;
        }
        var now = LocalDateTime.now();
        setCreatedAt(now);
        setUpdatedAt(now);
        setCreatedBy();
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(LocalDateTime.now());
        setLastModifiedBy();
    }

    public void setCreatedBy() {
        if (nonNull(getAuthentication()) && getAuthentication().getPrincipal() instanceof UserDetails) {
            var userDetails = (UserDetailsImpl) getAuthentication().getPrincipal();
            setCreatedBy(userDetails.getId());
            setLastModifiedBy(userDetails.getId());
        }
    }

    public void setLastModifiedBy() {
        if (nonNull(getAuthentication()) && getAuthentication().getPrincipal() instanceof UserDetails) {
            var userDetails = (UserDetailsImpl) getAuthentication().getPrincipal();
            setLastModifiedBy(userDetails.getId());
        }
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
