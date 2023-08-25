package com.library.online_library.repository.author;

import com.library.online_library.model.entity.author.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    List<AuthorEntity> findByIdIn(Set<Long> longs);

    boolean existsByFullName(String fullName);
}
