package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import com.example.burgerservice.rest.dto.IngredientTypeListWrapper;
import com.example.burgerservice.rest.mapper.IngredientTypeMapper;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {IngredientTypeServiceImpl.class, IngredientTypeMapper.class, Util.class})
@ActiveProfiles("test")
//@ConfigurationProperties(prefix = "service")
public class IngredientTypeServiceTest {

    private final IngredientTypeService ingredientTypeService;
    private final IngredientTypeMapper ingredientTypeMapper;

    @Value("${service.ingredient.str}")
    private String str;

    @MockBean
    private IngredientTypeRepository ingredientTypeRepository;


    @Autowired
    public IngredientTypeServiceTest(IngredientTypeService ingredientTypeService,
                                     IngredientTypeMapper ingredientTypeMapper) {
        this.ingredientTypeService = ingredientTypeService;
        this.ingredientTypeMapper = ingredientTypeMapper;
    }

     private List<IngredientType> createIngredientTypes() {

         System.out.println("");
         System.out.println(str);
         System.out.println("");


        IngredientType ingredientType1 = new IngredientType();
        ingredientType1.setId("A");
        ingredientType1.setName("AAA");
        IngredientType ingredientType2 = new IngredientType();
        ingredientType1.setId("B");
        ingredientType1.setName("BBB");
        IngredientType ingredientType3 = new IngredientType();
        ingredientType1.setId("C");
        ingredientType1.setName("CCC");
        return Arrays.asList(ingredientType1, ingredientType2, ingredientType3);
    }

    @Test
    void getAllIngredientTypesTest() {
        Mockito.when(ingredientTypeRepository.findAll()).thenReturn(createIngredientTypes());
        IngredientTypeListWrapper ingredientTypeListWrapper = new IngredientTypeListWrapper();

        ingredientTypeListWrapper.setIngredientTypes(
                createIngredientTypes().stream()
                .map(ingredientTypeMapper::ingredientTypeDao2Dto)
                .collect(Collectors.toList()));
        assertEquals(ingredientTypeListWrapper, ingredientTypeService.getAllIngredientTypes());
    }
}
