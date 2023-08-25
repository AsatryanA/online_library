package com.library.online_library.service.author;

import com.library.online_library.model.dto.author.AuthorCreateRequest;
import com.library.online_library.model.dto.author.AuthorDTO;
import com.library.online_library.model.dto.author.AuthorUpdateRequest;

import java.util.List;

public interface AuthorService {
    AuthorDTO create(AuthorCreateRequest authorCreateRequest);

    AuthorDTO update(Long id, AuthorUpdateRequest authorUpdateRequest);

    void delete(Long id);

    AuthorDTO getById(Long id);

    List<AuthorDTO> getAll();
}
