package com.library.online_library.repository.basket;

import com.library.online_library.model.entity.basket.BasketItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BasketItemRepository extends JpaRepository<BasketItemEntity, Long> {
     List<BasketItemEntity> findAllByUserId(Long userId);
     Optional<BasketItemEntity> findByUserIdAndBookId(Long userId,Long bookId);

     void deleteByUserIdAndBookId(Long id, Long bookId);

     boolean existsByUserIdAndBookId(Long userId, Long bookId);

    void deleteAllByUserId(Long userId);
}