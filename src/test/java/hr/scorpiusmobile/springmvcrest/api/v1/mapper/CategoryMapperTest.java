package hr.scorpiusmobile.springmvcrest.api.v1.mapper;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CategoryDTO;
import hr.scorpiusmobile.springmvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Djuro";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(categoryDTO.getId(), ID);
        assertEquals(categoryDTO.getName(), NAME);

    }
}
