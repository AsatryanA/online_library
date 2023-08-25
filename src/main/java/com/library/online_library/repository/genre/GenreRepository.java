package com.library.online_library.repository.genre;

import com.library.online_library.model.entity.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    List<GenreEntity> findByIdIn(Set<Long> longs);

    boolean existsByGenre(String genre);
}