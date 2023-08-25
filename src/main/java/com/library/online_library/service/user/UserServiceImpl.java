package com.library.online_library.service.user;

import com.library.online_library.exeption.CommonValidationException;
import com.library.online_library.mapper.user.UserMapper;
import com.library.online_library.model.dto.user.UserDTO;
import com.library.online_library.model.dto.user.UserUpdateRequest;
import com.library.online_library.repository.basket.BasketItemRepository;
import com.library.online_library.repository.card.CardRepository;
import com.library.online_library.repository.order.OrderRepository;
import com.library.online_library.repository.refresh_token.RefreshTokenRepository;
import com.library.online_library.repository.user.UserRepository;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.utils.CurrentUser;
import com.library.online_library.utils.constants.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FindOne findOne;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final OrderRepository orderRepository;
    private final BasketItemRepository basketItemRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Page<UserDTO> getAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO selfGet() {
        var userEntity = findOne.getUserById(CurrentUser.getId());
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public UserDTO selfUpdate(UserUpdateRequest userUpdateRequest) {
        var userEntity = findOne.getUserById(CurrentUser.getId());
        userMapper.updateEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public void selfDelete() {
        var userId = CurrentUser.getId();
        var userEntity = findOne.getUserById(userId);
        validateSelfDeletion(userId);
        deleteUserRelations(userId);
        userRepository.delete(userEntity);
    }

//    TODO: think about update of liked authors and genres, adding, deleting, think about what will happen when admin deletes author or genre

    private void validateSelfDeletion(Long userId) {
        if (orderRepository.existsByUserIdAndStatusIn(userId, List.of(OrderStatus.ACCEPTED, OrderStatus.COLLECTED, OrderStatus.ON_THE_WAY, OrderStatus.PENDING))) {
            throw new CommonValidationException("Cannot delete account,you have active orders");
        }
    }

    private void deleteUserRelations(Long userId) {
        basketItemRepository.deleteAllByUserId(userId);
        refreshTokenRepository.deleteAllByUserId(userId);
        orderRepository.deleteAllByUserId(userId);
        cardRepository.deleteAllByUserId(userId);
    }
}
