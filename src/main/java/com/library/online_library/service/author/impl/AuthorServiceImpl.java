package com.library.online_library.service.author.impl;

import com.library.online_library.mapper.author.AuthorMapper;
import com.library.online_library.model.dto.author.AuthorCreateRequest;
import com.library.online_library.model.dto.author.AuthorDTO;
import com.library.online_library.model.dto.author.AuthorUpdateRequest;
import com.library.online_library.repository.author.AuthorRepository;
import com.library.online_library.service.author.AuthorService;
import com.library.online_library.service.find_one.FindOne;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final FindOne findOne;
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    @Override
    @Transactional
    public AuthorDTO create(AuthorCreateRequest authorCreateRequest) {
        if(authorRepository.existsByFullName(authorCreateRequest.getFullName())) {
            throw new EntityExistsException("Author with this name " + authorCreateRequest.getFullName() + " already exists");
        }
        var authorEntity = authorMapper.toEntity(authorCreateRequest);
        authorRepository.save(authorEntity);
        return authorMapper.toDto(authorEntity);
    }

    @Override
    @Transactional
    public AuthorDTO update(Long id, AuthorUpdateRequest authorUpdateRequest) {
        if(authorRepository.existsByFullName(authorUpdateRequest.getFullName())) {
            throw new EntityExistsException("Author with this name " + authorUpdateRequest.getFullName() + " already exists");
        }
        var authorEntity = findOne.getAuthorEntity(id);
        authorMapper.updateEntity(authorUpdateRequest, authorEntity);
        authorRepository.save(authorEntity);
        return authorMapper.toDto(authorEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getById(Long id) {
        var authorEntity = findOne.getAuthorEntity(id);
        return authorMapper.toDto(authorEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDTO> getAll() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }
}
