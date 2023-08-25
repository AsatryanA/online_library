package com.library.online_library.service.auth;

import com.library.online_library.configuration.security.jwt.JwtTokenProvider;
import com.library.online_library.exeption.RefreshTokenNotFoundException;
import com.library.online_library.mapper.user.UserMapper;
import com.library.online_library.model.dto.auth.AuthRequest;
import com.library.online_library.model.dto.auth.AuthResponseDTO;
import com.library.online_library.model.dto.auth.UserRegisterDTO;
import com.library.online_library.model.entity.author.AuthorEntity;
import com.library.online_library.model.entity.genre.GenreEntity;
import com.library.online_library.model.entity.role.RoleEntity;
import com.library.online_library.repository.author.AuthorRepository;
import com.library.online_library.repository.genre.GenreRepository;
import com.library.online_library.repository.user.UserRepository;
import com.library.online_library.service.UserDetailsImpl;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.service.refresh_token.RefreshTokenService;
import com.library.online_library.utils.constants.RoleEnum;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
private final FindOne findOne;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponseDTO login(AuthRequest authRequest) {
        var email = authRequest.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                authRequest.getPassword()));
        return createAuthorizationResponse(authRequest);
    }

    @Override
    @Transactional
    public AuthResponseDTO renew(String refreshToken) {
        var jwt = jwtTokenProvider.parseJwt(refreshToken);
        if (!refreshTokenService.exists(jwt)) {
            throw new RefreshTokenNotFoundException("Refresh token not found");
        }
        refreshTokenService.delete(jwt);
        jwtTokenProvider.validateJwtTokenSignature(jwt);
        return getAuthResponseDTO();
    }

    @Override
    @Transactional
    public void register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new EntityExistsException("User with email " + userRegisterDTO.getEmail() + " already exists");
        }
        var userEntity = userMapper.toEntity(userRegisterDTO);
        userEntity.setRole(findOne.getRoleEntity(RoleEnum.ROLE_USER));
        if (Objects.nonNull(userRegisterDTO.getAuthorIds()) && !(userRegisterDTO.getAuthorIds().isEmpty())) {
           var authorsById = authorRepository.findByIdIn(userRegisterDTO.getAuthorIds());
            userEntity.setAuthors(authorsById);
        }
        if (Objects.nonNull(userRegisterDTO.getGenreIds()) && !(userRegisterDTO.getGenreIds().isEmpty())) {
           var genreById = genreRepository.findByIdIn(userRegisterDTO.getGenreIds());
            userEntity.setGenres(genreById);
        }
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(userEntity);
    }

    private AuthResponseDTO getAuthResponseDTO() {
        var authEmployeeRequestDTO = new AuthRequest();
        authEmployeeRequestDTO.setEmail(getAuthenticatedUserDetails().getUsername());
        authEmployeeRequestDTO.setPassword(getAuthenticatedUserDetails().getPassword());
        return createAuthorizationResponse(authEmployeeRequestDTO);
    }

    private UserDetails getAuthenticatedUserDetails() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    private AuthResponseDTO createAuthorizationResponse(AuthRequest authRequest) {
        var email = authRequest.getEmail();
        var user = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);

        var roles = getRoles(user);
        var role = roles.get(0);
        var accessToken = jwtTokenProvider.generateAccessToken(
                user.getId(),
                email,
                role
        );

        var refreshToken = jwtTokenProvider.generateRefreshToken(
                accessToken,
                role,
                user.getId());
        var authResponseModel = new AuthResponseDTO();
        authResponseModel.setAccessToken(accessToken);
        authResponseModel.setRefreshToken(refreshToken);
        authResponseModel.setExpiresIn(jwtTokenProvider.getAccessTokenExpirationMs());
        authResponseModel.setUserId(user.getId());
        authResponseModel.setRoles(roles);
        return authResponseModel;
    }

    private List<String> getRoles(UserDetails user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }

}
