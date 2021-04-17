package bw.co.barbosa.fruitshop.api.v1.mapper;

import bw.co.barbosa.fruitshop.api.v1.dto.CategoryDTO;
import bw.co.barbosa.fruitshop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
