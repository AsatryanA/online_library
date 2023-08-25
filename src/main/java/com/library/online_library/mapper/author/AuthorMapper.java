package com.library.online_library.mapper.author;

import com.library.online_library.model.dto.author.AuthorCreateRequest;
import com.library.online_library.model.dto.author.AuthorDTO;
import com.library.online_library.model.dto.author.AuthorUpdateRequest;
import com.library.online_library.model.entity.author.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(AuthorEntity authorEntity);

    AuthorEntity toEntity(AuthorCreateRequest authorCreateRequest);

    void updateEntity(AuthorUpdateRequest authorUpdateRequest, @MappingTarget AuthorEntity authorEntity);
}
