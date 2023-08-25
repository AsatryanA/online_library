package com.library.online_library.service.auth;

import com.library.online_library.repository.user.UserRepository;
import com.library.online_library.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User by username: %s not found", username)));
        return UserDetailsImpl.build(userEntity);
    }
}