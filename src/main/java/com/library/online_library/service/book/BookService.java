package com.library.online_library.service.book;

import com.library.online_library.model.dto.book.BookCreateRequest;
import com.library.online_library.model.dto.book.BookDTO;
import com.library.online_library.model.dto.book.BookUpdateRequest;
import com.library.online_library.model.dto.resource.ResourceDto;
import com.library.online_library.model.dto.user.filter.BookFilterSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {

    BookDTO create(BookCreateRequest bookCreateRequest);

    BookDTO update(Long productId, BookUpdateRequest bookUpdateRequest);

    BookDTO getById(Long bookId);

    void delete(Long productId);

    Page<BookDTO> getAll(BookFilterSearchRequest filterSearchRequest, PageRequest pageRequest);

    ResourceDto uploadImage(MultipartFile file, Long id, Integer orderNumber);

    void deleteResource(Long id);

    Page<BookDTO> suggest(PageRequest pageRequest);
}
