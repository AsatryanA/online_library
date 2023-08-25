package com.library.online_library.repository.role;

import com.library.online_library.model.entity.role.RoleEntity;
import com.library.online_library.utils.constants.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(RoleEnum roleEnum);
}
