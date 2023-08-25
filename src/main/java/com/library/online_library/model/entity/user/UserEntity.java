package com.library.online_library.model.entity.user;

import com.library.online_library.model.entity.BaseEntity;
import com.library.online_library.model.entity.author.AuthorEntity;
import com.library.online_library.model.entity.card.CardEntity;
import com.library.online_library.model.entity.genre.GenreEntity;
import com.library.online_library.model.entity.role.RoleEntity;
import com.library.online_library.utils.constants.EntityName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = EntityName.USER)
public class UserEntity extends BaseEntity {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(unique = true,nullable = false)
    private String email;

    @Column
    private String address;
    @Column
    private String postalZip;
    @Column(nullable = false)
    private String password;
    @Column
    private String country;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "authors_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "genres_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RoleEntity role;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "user")
    private List<CardEntity> cards;

}
