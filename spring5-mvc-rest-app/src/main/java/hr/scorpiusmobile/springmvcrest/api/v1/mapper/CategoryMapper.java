package hr.scorpiusmobile.springmvcrest.api.v1.mapper;

import hr.scorpiusmobile.springmvcrest.api.v1.model.CategoryDTO;
import hr.scorpiusmobile.springmvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

}
