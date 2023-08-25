package com.library.online_library.model.dto.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class BaseBookDTO {

    @NotNull
    private String title;

    private String description;

    private String img;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private String isbn;

    private LocalDateTime published;

    private String publisher;
}
