package com.library.online_library.model.entity.role;

import com.library.online_library.utils.constants.EntityName;
import com.library.online_library.utils.constants.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = EntityName.ROLE)
public class RoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
