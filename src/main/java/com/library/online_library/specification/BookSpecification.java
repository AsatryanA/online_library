package com.library.online_library.specification;

import com.library.online_library.model.dto.user.filter.BookFilterSearchRequest;
import com.library.online_library.model.entity.author.AuthorEntity;
import com.library.online_library.model.entity.author.AuthorEntity_;
import com.library.online_library.model.entity.book.BookEntity;
import com.library.online_library.model.entity.book.BookEntity_;
import com.library.online_library.model.entity.genre.GenreEntity;
import com.library.online_library.model.entity.genre.GenreEntity_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class BookSpecification {

    public Specification<BookEntity> search(BookFilterSearchRequest bookFilterSearchRequest) {

        return Specification.where((root, query, criteriaBuilder) -> {
            if(Objects.isNull(bookFilterSearchRequest)){
                return criteriaBuilder.conjunction();
            }
            var predicates = new ArrayList<Predicate>();

            predicates.add(filter(bookFilterSearchRequest.getFilter()).toPredicate(root, query, criteriaBuilder));
            predicates.add(searchByField(bookFilterSearchRequest.getSearch()).toPredicate(root, query, criteriaBuilder));
            predicates.add(fullTextSearch(bookFilterSearchRequest.getSearch()).toPredicate(root, query, criteriaBuilder));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    private Specification<BookEntity> searchByField(BookFilterSearchRequest.SearchModel search) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {

            if (Objects.nonNull(search)) {

                Predicate searchPredicate;
                var predicates = new ArrayList<Predicate>();

                if (nonNull(search.getTitle())) {
                    searchPredicate = criteriaBuilder
                            .like(root.get(BookEntity_.title), search.getTitle());
                    predicates.add(searchPredicate);
                }
                if (nonNull(search.getDescription())) {
                    searchPredicate = criteriaBuilder
                            .like(root.get(BookEntity_.description), search.getDescription());
                    predicates.add(searchPredicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.conjunction();
        });
    }

    private Specification<BookEntity> fullTextSearch(BookFilterSearchRequest.SearchModel search) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {

            if (Objects.nonNull(search)) {

                Predicate searchPredicate;
                var predicates = new ArrayList<Predicate>();

                searchPredicate = criteriaBuilder
                        .like(root.get(BookEntity_.title), search.getTitle());
                predicates.add(searchPredicate);

                searchPredicate = criteriaBuilder
                        .like(root.get(BookEntity_.description), search.getDescription());
                predicates.add(searchPredicate);

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.conjunction();
        });
    }

    private Specification<BookEntity> filter(BookFilterSearchRequest.FilterModel filter) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {

            if (Objects.nonNull(filter)) {

                Predicate searchPredicate;
                var predicates = new ArrayList<Predicate>();

                Join<BookEntity, GenreEntity> genreJoin = root.join(BookEntity_.genre);
                Join<BookEntity, AuthorEntity> authorJoin = root.join(BookEntity_.author);
                if (nonNull(filter.getGenre())) {
                    searchPredicate = criteriaBuilder
                            .equal(genreJoin.get(GenreEntity_.genre), filter.getGenre());
                    predicates.add(searchPredicate);
                }
                if (nonNull(filter.getAuthor())) {
                    searchPredicate = criteriaBuilder
                            .equal(authorJoin.get(AuthorEntity_.fullName), filter.getAuthor());
                    predicates.add(searchPredicate);
                }
                if (nonNull(filter.getStartPrice())) {
                    searchPredicate = criteriaBuilder
                            .greaterThanOrEqualTo(root.get(BookEntity_.price), filter.getStartPrice());
                    predicates.add(searchPredicate);
                }
                if (nonNull(filter.getEndPrice())) {
                    searchPredicate = criteriaBuilder
                            .lessThanOrEqualTo(root.get(BookEntity_.price), filter.getEndPrice());
                    predicates.add(searchPredicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.conjunction();
        });
    }

    public Specification<BookEntity> suggest(BookFilterSearchRequest.FilterModel filterModel) {
        return Specification.where((root, query, criteriaBuilder) -> {
            Predicate searchPredicate;
            var predicates = new ArrayList<Predicate>();

            if(!filterModel.getAuthorsIds().isEmpty()) {
                searchPredicate = root.in(
                        root
                                .join(BookEntity_.author)
                                .get(AuthorEntity_.id),
                        filterModel.getAuthorsIds()
                );
                predicates.add(searchPredicate);
            }
            if(!filterModel.getAuthorsIds().isEmpty()) {
                searchPredicate = root.in(
                        root
                                .join(BookEntity_.genre)
                                .get(GenreEntity_.id),
                        filterModel.getGenresIds()
                );
                predicates.add(searchPredicate);
            }
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
    }
}
