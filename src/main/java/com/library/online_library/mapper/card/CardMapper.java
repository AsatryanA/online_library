package com.library.online_library.mapper.card;

import com.library.online_library.model.dto.card.CardCreateRequest;
import com.library.online_library.model.dto.card.CardDTO;
import com.library.online_library.model.dto.card.CardUpdateRequest;
import com.library.online_library.model.entity.card.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardEntity toEntity(CardCreateRequest cardCreateRequest);

    void updateEntity(CardUpdateRequest cardUpdateRequest, @MappingTarget CardEntity cardEntity);

    CardDTO toDto(CardEntity cardEntity);
}
