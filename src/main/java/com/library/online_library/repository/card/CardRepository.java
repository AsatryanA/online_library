package com.library.online_library.repository.card;

import com.library.online_library.model.entity.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByUserId(Long userId);

    Optional<CardEntity> findByIdAndUserId(Long cardId, Long userId);

    void deleteAllByUserId(Long userId);
}