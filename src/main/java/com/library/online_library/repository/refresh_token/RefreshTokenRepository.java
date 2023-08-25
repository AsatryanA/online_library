package com.library.online_library.repository.refresh_token;

import com.library.online_library.model.entity.refresh_token.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    boolean existsByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);

    Optional<RefreshTokenEntity> findByUserId(Long id);

    void deleteAllByUserId(Long userId);


    @Modifying
    @Query(nativeQuery = true,
            countQuery = "SELECT count(*) from refresh_token",
            value = """
                    delete 
                    from refresh_token
                    where created_at < (now() - interval '30 days')
                    """)
    void deleteOldTokens();
}
