package com.library.online_library.service.find_one;


import com.library.online_library.exeption.ResourceNotFoundException;
import com.library.online_library.model.entity.author.AuthorEntity;
import com.library.online_library.model.entity.book.BookEntity;
import com.library.online_library.model.entity.genre.GenreEntity;
import com.library.online_library.model.entity.order.OrderEntity;
import com.library.online_library.model.entity.role.RoleEntity;
import com.library.online_library.model.entity.user.UserEntity;
import com.library.online_library.repository.author.AuthorRepository;
import com.library.online_library.repository.book.BookRepository;
import com.library.online_library.repository.genre.GenreRepository;
import com.library.online_library.repository.order.OrderRepository;
import com.library.online_library.repository.role.RoleRepository;
import com.library.online_library.repository.user.UserRepository;
import com.library.online_library.utils.CurrentUser;
import com.library.online_library.utils.constants.EntityName;
import com.library.online_library.utils.constants.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindOne {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final OrderRepository orderRepository;
    private final AuthorRepository authorRepository;


    public RoleEntity getRoleEntity(RoleEnum roleEnum) {
        return roleRepository.findByRole(roleEnum).orElseThrow(
                () -> new ResourceNotFoundException(EntityName.ROLE, roleEnum.name()));
    }


    public UserEntity getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.USER, userId));
    }

    public GenreEntity getGenreEntity(Long categoryId) {
        return genreRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(EntityName.GENRE, categoryId));
    }

    public OrderEntity getOrderEntity(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.ORDER, orderId));
    }

    public OrderEntity getUserOrderEntity(Long orderId) {
        return orderRepository.findByIdAndUserId(orderId, CurrentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.ORDER, orderId));
    }

    public BookEntity getBookEntity(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.BOOK, bookId));
    }

    public AuthorEntity getAuthorEntity(Long authorId) {
        return authorRepository
                .findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.AUTHOR, authorId));
    }
}
