package com.library.online_library.service.genre;

import com.library.online_library.model.dto.genre.GenreCreateRequest;
import com.library.online_library.model.dto.genre.GenreDTO;
import com.library.online_library.model.dto.genre.GenreUpdateRequest;

import java.util.List;

public interface GenreService {

    GenreDTO create(GenreCreateRequest categoryCreateRequest);

    GenreDTO update(Long categoryId, GenreUpdateRequest categoryUpdateRequest);

    void delete(Long categoryId);

    GenreDTO getById(Long categoryId);

    List<GenreDTO> getAll();
}