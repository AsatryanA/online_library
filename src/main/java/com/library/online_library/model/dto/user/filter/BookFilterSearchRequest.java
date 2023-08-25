package com.library.online_library.model.dto.user.filter;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static com.library.online_library.utils.SearchUtils.addWildCardForSearch;

@Setter
@Getter
public class BookFilterSearchRequest {

    private String searchBy;

    private FilterModel filter;

    private SearchModel search;

    @Setter
    @Getter
    public static class FilterModel {
        private String genre;
        private String author;
        private BigDecimal startPrice;
        private BigDecimal endPrice;
        private Set<Long> authorsIds = new HashSet<>();
        private Set<Long> genresIds = new HashSet<>();
    }

    @Getter
    public static class SearchModel {
        private String title;
        private String description;

        public void setTitle(String title) {
            this.title = addWildCardForSearch(title);
        }

        public void setDescription(String description) {
            this.description = addWildCardForSearch(title);
        }

    }

}
