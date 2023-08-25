package com.library.online_library.service.card;

import com.library.online_library.model.dto.card.CardCreateRequest;
import com.library.online_library.model.dto.card.CardDTO;
import com.library.online_library.model.dto.card.CardUpdateRequest;

import java.util.List;

public interface CardService {
    CardDTO create(CardCreateRequest cardCreateRequest);

    List<CardDTO> getAll();

    void delete(Long cardId);

}
