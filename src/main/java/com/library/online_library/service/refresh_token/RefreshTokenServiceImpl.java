package com.library.online_library.service.refresh_token;

import com.library.online_library.model.entity.refresh_token.RefreshTokenEntity;
import com.library.online_library.repository.refresh_token.RefreshTokenRepository;
import com.library.online_library.service.find_one.FindOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class       RefreshTokenServiceImpl implements RefreshTokenService {

    private final FindOne findOne;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void save(Long id, String token) {
        var refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setUser(findOne.getUserById(id));
        refreshTokenEntity.setRefreshToken(token.substring(token.lastIndexOf(".")));
        refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    @Transactional
    public void delete(String token) {
        refreshTokenRepository.deleteByRefreshToken(token.substring(token.lastIndexOf(".")));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(String token) {
        return refreshTokenRepository.existsByRefreshToken(token.substring(token.lastIndexOf(".")));
    }

}
