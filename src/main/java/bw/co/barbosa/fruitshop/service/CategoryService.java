package bw.co.barbosa.fruitshop.service;

import bw.co.barbosa.fruitshop.api.v1.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
