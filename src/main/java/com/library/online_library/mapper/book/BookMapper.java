package com.library.online_library.mapper.book;

import com.library.online_library.model.dto.book.BookCreateRequest;
import com.library.online_library.model.dto.book.BookDTO;
import com.library.online_library.model.dto.book.BookUpdateRequest;
import com.library.online_library.model.dto.user.filter.BookFilterSearchRequest;
import com.library.online_library.model.entity.book.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDto(BookEntity bookEntity);
    BookEntity toEntity(BookCreateRequest bookCreateRequest);
    void updateEntity(BookUpdateRequest bookUpdateRequest, @MappingTarget BookEntity bookEntity);

    @Mapping(target = "title", source = "searchBy")
    @Mapping(target = "description", source = "searchBy")
    BookFilterSearchRequest.SearchModel toSearchModel(String searchBy);


}
