package hr.scorpiusmobile.springmvcrest.controllers.v1;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CategoryDTO;
import hr.scorpiusmobile.springmvcrest.api.v1.model.CategoryListDTO;
import hr.scorpiusmobile.springmvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategroyByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);

    }


}
