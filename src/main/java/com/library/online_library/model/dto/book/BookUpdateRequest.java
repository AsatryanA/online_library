package com.library.online_library.model.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BookUpdateRequest extends BaseBookDTO{
    @NotNull
    private Long authorId;

    @NotNull
    private Long genreId;
}
