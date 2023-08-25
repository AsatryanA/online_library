package com.library.online_library.service.super_admin.impl;

import com.library.online_library.mapper.user.UserMapper;
import com.library.online_library.model.dto.auth.AdminRegisterDTO;
import com.library.online_library.model.dto.user.UserDTO;
import com.library.online_library.repository.user.UserRepository;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.service.super_admin.SuperAdminService;
import com.library.online_library.utils.constants.RoleEnum;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FindOne findOne;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO register(AdminRegisterDTO adminRegisterDTO) {
        if (userRepository.existsByEmail(adminRegisterDTO.getEmail())) {
            throw new EntityExistsException("Admin with email " + adminRegisterDTO.getEmail() + " already exists");
        }
        var userEntity = userMapper.toEntity(adminRegisterDTO);
        userEntity.setRole(findOne.getRoleEntity(RoleEnum.ROLE_ADMIN));

        userEntity.setPassword(passwordEncoder.encode(adminRegisterDTO.getPassword()));
        userRepository.save(userEntity);

        return userMapper.toDto(userEntity);
    }
}
