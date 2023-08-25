package com.library.online_library.controller.book.user;

import com.library.online_library.model.dto.book.BookDTO;
import com.library.online_library.model.dto.user.filter.BookFilterSearchRequest;
import com.library.online_library.service.book.BookService;
import com.library.online_library.utils.pagination.CustomPageRequest;
import com.library.online_library.utils.pagination.sort.ImplSort;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/books")
public class BookUserController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<BookDTO>> getBooks(
            @RequestBody(required = false) BookFilterSearchRequest filterSearchRequest,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction) {
        var pageRequest = CustomPageRequest.from(page, size, ImplSort.by("id", direction));
        return ResponseEntity.ok(bookService.getAll(filterSearchRequest, pageRequest));
    }

    @GetMapping("/suggest")
    @PreAuthorize("@hasRole(@role.ROLE_USER)")
    public ResponseEntity<Page<BookDTO>> suggestBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction)
    {
        var pageRequest = CustomPageRequest.from(page, size, ImplSort.by("id", direction));
        return ResponseEntity.ok(bookService.suggest(pageRequest));
    }
}
