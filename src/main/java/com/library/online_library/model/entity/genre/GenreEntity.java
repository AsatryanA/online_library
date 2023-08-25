package com.library.online_library.model.entity.genre;

import com.library.online_library.model.entity.BaseEntity;
import com.library.online_library.model.entity.book.BookEntity;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = EntityName.GENRE)
public class GenreEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String genre;

}
