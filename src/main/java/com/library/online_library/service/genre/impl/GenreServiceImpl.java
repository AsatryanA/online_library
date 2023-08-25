package com.library.online_library.service.genre.impl;

import com.library.online_library.mapper.genre.GenreMapper;
import com.library.online_library.model.dto.genre.GenreCreateRequest;
import com.library.online_library.model.dto.genre.GenreDTO;
import com.library.online_library.model.dto.genre.GenreUpdateRequest;
import com.library.online_library.repository.genre.GenreRepository;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.service.genre.GenreService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final FindOne findOne;
    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public GenreDTO create(GenreCreateRequest genreCreateRequest) {
        if(genreRepository.existsByGenre(genreCreateRequest.getGenre())) {
            throw new EntityExistsException("Genre with this name " + genreCreateRequest.getGenre() + " already exists");
        }
        var categoryEntity = genreMapper.toEntity(genreCreateRequest);
        genreRepository.save(categoryEntity);
        return genreMapper.toDto(categoryEntity);
    }

    @Override
    @Transactional
    public GenreDTO update(Long categoryId, GenreUpdateRequest genreUpdateRequest) {
        if(genreRepository.existsByGenre(genreUpdateRequest.getGenre())) {
            throw new EntityExistsException("Genre with this name " + genreUpdateRequest.getGenre() + " already exists");
        }
        var categoryEntity = findOne.getGenreEntity(categoryId);
        genreMapper.updateEntity(genreUpdateRequest, categoryEntity);
        genreRepository.save(categoryEntity);
        return genreMapper.toDto(categoryEntity);
    }

    @Override
    @Transactional
    public void delete(Long categoryId) {
        genreRepository.deleteById(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDTO getById(Long genreId) {
        var genreEntity = findOne.getGenreEntity(genreId);
        return genreMapper.toDto(genreEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDTO> getAll() {
        return genreRepository
                .findAll()
                .stream()
                .map(genreMapper::toDto)
                .toList();
    }
}
