package com.library.online_library.service.book.impl;

import com.library.online_library.mapper.book.BookMapper;
import com.library.online_library.model.dto.book.BookCreateRequest;
import com.library.online_library.model.dto.book.BookDTO;
import com.library.online_library.model.dto.book.BookUpdateRequest;
import com.library.online_library.model.dto.resource.ResourceDto;
import com.library.online_library.model.dto.user.filter.BookFilterSearchRequest;
import com.library.online_library.model.entity.author.AuthorEntity;
import com.library.online_library.model.entity.book.BookEntity;
import com.library.online_library.model.entity.genre.GenreEntity;
import com.library.online_library.model.entity.user.UserEntity;
import com.library.online_library.repository.book.BookRepository;
import com.library.online_library.service.book.BookService;
import com.library.online_library.service.find_one.FindOne;
import com.library.online_library.specification.BookSpecification;
import com.library.online_library.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final FindOne findOne;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecification bookSpecification;

    @Override
    @Transactional
    public BookDTO create(BookCreateRequest bookCreateRequest) {
        var authorEntity = findOne.getAuthorEntity(bookCreateRequest.getAuthorId());
        var genreEntity = findOne.getGenreEntity(bookCreateRequest.getGenreId());
        var bookEntity = bookMapper.toEntity(bookCreateRequest);
        bookEntity.setAuthor(authorEntity);
        bookEntity.setGenre(genreEntity);
        bookRepository.save(bookEntity);
        return bookMapper.toDto(bookEntity);
    }

    @Override
    @Transactional
    public BookDTO update(Long bookId, BookUpdateRequest bookUpdateRequest) {
        var authorEntity = findOne.getAuthorEntity(bookUpdateRequest.getAuthorId());
        var genreEntity = findOne.getGenreEntity(bookUpdateRequest.getGenreId());
        var bookEntity = findOne.getBookEntity(bookId);
        bookMapper.updateEntity(bookUpdateRequest, bookEntity);
        bookEntity.setAuthor(authorEntity);
        bookEntity.setGenre(genreEntity);
        bookRepository.save(bookEntity);
        return bookMapper.toDto(bookEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public BookDTO getById(Long bookId) {
        var bookEntity = findOne.getBookEntity(bookId);
        return bookMapper.toDto(bookEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookDTO> getAll(BookFilterSearchRequest filterSearchRequest, PageRequest pageRequest) {
        return bookRepository.findAll(bookSpecification.search(filterSearchRequest), pageRequest).map(bookMapper::toDto);
    }

    @Override
    public ResourceDto uploadImage(MultipartFile file, Long id, Integer orderNumber) {
        //save to storage
        return ResourceDto.builder().url("https://images.theconversation.com/files/45159/original/rptgtpxd-1396254731.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&h=502&fit=crop&dpr=1").build();
    }

    @Override
    @Transactional
    public void deleteResource(Long id) {
        //delete from storage
        BookEntity bookEntity = findOne.getBookEntity(id);
        bookEntity.setImage(null);
        bookRepository.save(bookEntity);
    }

    @Override
    @Transactional
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public Page<BookDTO> suggest(PageRequest pageRequest) {
        var userEntity = findOne.getUserById(CurrentUser.getId());
        var authorsIds = userEntity.getAuthors().stream().map(AuthorEntity::getId).collect(Collectors.toSet());
        var genresIds = userEntity.getGenres().stream().map(GenreEntity::getId).collect(Collectors.toSet());
        var filterModel = new BookFilterSearchRequest.FilterModel();
        filterModel.setAuthorsIds(authorsIds);
        filterModel.setGenresIds(genresIds);
        return bookRepository.findAll(bookSpecification.suggest(filterModel), pageRequest).map(bookMapper::toDto);
    }
}
