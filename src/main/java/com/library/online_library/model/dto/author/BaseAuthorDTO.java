package com.library.online_library.model.dto.author;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseAuthorDTO {

    @NotNull
    private String fullName;
}
