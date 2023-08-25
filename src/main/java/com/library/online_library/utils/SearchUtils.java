package com.library.online_library.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class SearchUtils {

    public static String addWildCardForSearch(String str) {
        return "%" + str.trim().strip().toLowerCase() + "%";
    }

    public static List<String> splitSearchStrBySpace(String searchStr) {
        return new ArrayList<>(Arrays.asList(searchStr.split(" ")));
    }
}
