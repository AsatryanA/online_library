package com.library.online_library.model.dto.book;

import com.library.online_library.model.dto.author.AuthorDTO;
import com.library.online_library.model.dto.genre.GenreDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookDTO extends BaseBookDTO {

    private Long id;

    private GenreDTO genre;

    private AuthorDTO author;

}
