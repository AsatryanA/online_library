package com.library.online_library.mapper;

public interface MapperSpecification<SearchModel> {

    SearchModel toSearchModel(String searchBy);
}
