package hr.scorpiusmobile.springmvcrest.services;

import hr.scorpiusmobile.springmvcrest.api.v1.mapper.CategoryMapper;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CategoryDTO;
import hr.scorpiusmobile.springmvcrest.domain.Category;
import hr.scorpiusmobile.springmvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    public static final String NAME_PERO = "Pero";
    public static final String NAME_DJURO = "Djuro";
    public static final long ID_1 = 1L;
    public static final long ID_2 = 2L;
    @Mock
    CategoryRepository categoryRepository;
    CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {

        Category category1 = new Category();

        Category category2 = new Category();
        category1.setId(ID_1);
        category1.setName(NAME_PERO);
        category2.setId(ID_2);
        category2.setName(NAME_DJURO);
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = new ArrayList<>();

        categoryDTOs = categoryService.getAllCategories();

        assertEquals(categories.size(), categoryDTOs.size());

    }

    @Test
    void getCategoryByName() {

        Category category1 = new Category();
        category1.setId(ID_1);
        category1.setName(NAME_PERO);

        when(categoryRepository.findByName(anyString())).thenReturn(category1);

        CategoryDTO returnedCategory = categoryService.getCategoryByName(NAME_PERO);

        assertEquals(ID_1, returnedCategory.getId());
        assertEquals(NAME_PERO, returnedCategory.getName());

    }
}