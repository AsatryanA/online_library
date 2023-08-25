package com.library.online_library.mapper.genre;

import com.library.online_library.model.dto.genre.GenreCreateRequest;
import com.library.online_library.model.dto.genre.GenreDTO;
import com.library.online_library.model.dto.genre.GenreUpdateRequest;
import com.library.online_library.model.entity.genre.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDto(GenreEntity genreEntity);

    GenreEntity toEntity(GenreCreateRequest genreCreateRequest);

    void updateEntity(GenreUpdateRequest genreUpdateRequest, @MappingTarget GenreEntity genreEntity);

}
