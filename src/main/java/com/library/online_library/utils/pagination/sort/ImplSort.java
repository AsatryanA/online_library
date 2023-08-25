package com.library.online_library.utils.pagination.sort;

import org.springframework.data.domain.Sort;

public class ImplSort {
    public static Sort by(String sortProp, Sort.Direction sortDirection) {
        return Sort.by(sortDirection, sortProp, "id");
    }
}
