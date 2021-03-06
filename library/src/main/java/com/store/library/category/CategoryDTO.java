package com.store.library.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Category Model")
public class CategoryDTO implements Comparable<CategoryDTO> {

    public CategoryDTO() {
    }
    
    public CategoryDTO(Integer integer, String label) {
        super();
        this.code = integer;
        this.label = label;
    }

    @ApiModelProperty(value = "Category code")
    private Integer code;
    
    @ApiModelProperty(value = "Category label")
    private String label;
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int compareTo(CategoryDTO o) {
        return label.compareToIgnoreCase(o.label);
    }

}

