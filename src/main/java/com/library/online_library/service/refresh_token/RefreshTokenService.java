package com.library.online_library.service.refresh_token;

public interface RefreshTokenService {

    void save(Long id, String token);

    void delete(String token);

    boolean exists(String token);

}
