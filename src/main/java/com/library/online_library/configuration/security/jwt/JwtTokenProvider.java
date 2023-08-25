package com.library.online_library.configuration.security.jwt;

import com.library.online_library.service.refresh_token.RefreshTokenService;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final RefreshTokenService refreshTokenService;
    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${accessTokenExpirationMs}")
    private long accessTokenExpirationMs;

    @Value("${refreshTokenExpirationMs}")
    private long refreshTokenExpirationMs;

    @Value("${tempTokenExpirationMs}")
    private long tempTokenExpirationMs;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Long id, String username, String role) {
        log.info("generate access token");
        var claims = Jwts.claims().setSubject(username);
        claims.put("id", id);
        claims.put("role", role);

        return buildJwt(claims, accessTokenExpirationMs);
    }

    public String generateOneTimeToken(String username) {
        log.info("generate one time token");
        var claims = Jwts.claims().setSubject(username);
        return buildJwt(claims, tempTokenExpirationMs);
    }

    public String generateResetPasswordToken(String username) {
        log.info("generate reset password token");
        var claims = Jwts.claims().setSubject(username);
        claims.put("reset", true);
        return buildJwt(claims, tempTokenExpirationMs);
    }

    public String generateRefreshToken(String accessToken, String role, Long id) {
        log.info("generate refresh token");
        var claims = Jwts.claims().setSubject(getSubject(accessToken));
        claims.put("accessToken", accessToken.substring(5, 15));
        claims.put("id", id);
        claims.put("role", role);
        var jwt = buildJwt(claims, refreshTokenExpirationMs);
        refreshTokenService.save(id, jwt);
        return jwt;
    }

    private String buildJwt(Claims claims, long jwtExpirationMs) {
        log.info("build jwt token");
        return Jwts.builder()
                .setClaims(claims)
                .claim("now_allow_duplication", Thread.currentThread().getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean jwtHasReset(String token) {
        try {
            log.info("check claims has 'reset' field ");
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("reset", Boolean.class);
        } catch (Exception ex) {
            log.error("Invalid JWT Signature. May 'reset' field does not exist");
            throw new SignatureException("Invalid JWT signature");
        }
    }

    public String getSubject(String token) {
        var subject = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        log.info(String.format("Subject is: %s", subject));
        return subject;
    }

    public boolean validateJwtTokenSignature(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        throw new AccessDeniedException("Invalid Jwt token");
    }

    public LocalDateTime getAccessTokenExpirationMs() {
        var seconds = accessTokenExpirationMs / 1000;
        return LocalDateTime.now().plusSeconds(seconds);
    }

    public String parseJwt(String token) {
        var tokenType = "Bearer ";
        log.info("jwt parsing");
        if (StringUtils.hasText(token) && token.startsWith(tokenType)) {
            log.info(token.substring(tokenType.length()));
            return token.substring(tokenType.length());
        }
        return null;
    }

    public String getRole(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().get("role", String.class);
    }

    public Long getId(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().get("id", Long.class);
    }

}
