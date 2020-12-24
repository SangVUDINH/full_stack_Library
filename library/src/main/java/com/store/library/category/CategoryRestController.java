package com.store.library.category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("rest/category/api")
@Api(value = "Book Category Rest Controller")
public class CategoryRestController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);
    
    @Autowired
    private ICategoryService categoryService;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    @ApiOperation(value="List all book categories of the Library", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfully listed"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<CategoryDTO>> getAllCategories (){
        
        List<Category> categories = categoryService.getAllCategories();
        
        if(!CollectionUtils.isEmpty( categories )) {
            categories.removeAll( Collections.singleton( null ) );
            
            List<CategoryDTO> categoriesDTO = categories.stream().map( category ->{
                return mapCategoryToCategoryDTO(category);
            }).collect( Collectors.toList());
            
            return new ResponseEntity<List<CategoryDTO>>(categoriesDTO, HttpStatus.OK);
        }
        
        return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NO_CONTENT);
        
        
        
    }

    private CategoryDTO mapCategoryToCategoryDTO( Category category ) {
        ModelMapper mapper = new ModelMapper();
        CategoryDTO catDTO = mapper.map(category, CategoryDTO.class);
        return catDTO;
        
    }
}
