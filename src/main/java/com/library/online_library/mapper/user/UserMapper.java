package com.library.online_library.mapper.user;

import com.library.online_library.model.dto.auth.AdminRegisterDTO;
import com.library.online_library.model.dto.auth.UserRegisterDTO;
import com.library.online_library.model.dto.user.UserDTO;
import com.library.online_library.model.dto.user.UserUpdateRequest;
import com.library.online_library.model.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserMapper{

    UserDTO toDto(UserEntity userEntity);

    void updateEntity(UserUpdateRequest userUpdateRequest, @MappingTarget UserEntity userEntity);
    default LocalDateTime fromLocalDate(LocalDate localDate) {
        return localDate == null ? null : localDate.atStartOfDay();
    }

    UserEntity toEntity(UserRegisterDTO userRegisterDTO);

    UserEntity toEntity(AdminRegisterDTO adminRegisterDTO);

}
