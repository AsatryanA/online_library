package com.library.online_library.model.dto.genre;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseGenreDTO {

    @NotNull
    private String genre;
}
