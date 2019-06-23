package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);

}
