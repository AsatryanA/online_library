package com.library.online_library.controller.book.admin;

import com.library.online_library.model.dto.book.BookCreateRequest;
import com.library.online_library.model.dto.book.BookDTO;
import com.library.online_library.model.dto.book.BookUpdateRequest;
import com.library.online_library.model.dto.resource.ResourceDto;
import com.library.online_library.service.book.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/admin/books")
@PreAuthorize("hasRole(@role.ROLE_ADMIN)")
public class BookAdminController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        return ResponseEntity.ok(bookService.create(bookCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable @Positive Long id, @RequestBody @Valid BookUpdateRequest bookUpdateRequest) {
        return ResponseEntity.ok(bookService.update(id, bookUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        bookService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }



    @PostMapping("{id}/image")
    public ResponseEntity<ResourceDto> uploadImage(@RequestBody MultipartFile file,
                                                   @PathVariable @Positive Long id,
                                                   @RequestParam Integer orderNumber) {
        return ResponseEntity.ok(bookService.uploadImage(file, id, orderNumber));
    }

    @DeleteMapping("{id}/image")
    public void deleteImage(@PathVariable @Positive Long id) {
        bookService.deleteResource(id);
    }
}
