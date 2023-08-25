package com.library.online_library.model.entity.book;

import com.library.online_library.model.entity.BaseEntity;
import com.library.online_library.model.entity.author.AuthorEntity;
import com.library.online_library.model.entity.genre.GenreEntity;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = EntityName.BOOK)
public class BookEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private String isbn;

    @Column
    private String image;

    @Column
    private LocalDateTime published;

    @Column
    private String publisher;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = false)
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private GenreEntity genre;
}
