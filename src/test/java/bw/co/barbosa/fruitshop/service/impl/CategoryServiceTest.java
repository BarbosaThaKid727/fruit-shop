package bw.co.barbosa.fruitshop.service.impl;

import bw.co.barbosa.fruitshop.api.v1.dto.CategoryDTO;
import bw.co.barbosa.fruitshop.api.v1.mapper.CategoryMapper;
import bw.co.barbosa.fruitshop.model.Category;
import bw.co.barbosa.fruitshop.repository.CategoryRepository;
import bw.co.barbosa.fruitshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Foo Bar";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImp(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategoriesTest() throws Exception {

        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        //when
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void getCategoryByNameTest() throws Exception {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //when
        when(categoryRepository.findByName(anyString())).thenReturn(category);
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}