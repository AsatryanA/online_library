package com.library.online_library.model.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BookDetails implements Serializable {
    private String title;
    private String isbn;
    private String author;
    private String genre;
}
