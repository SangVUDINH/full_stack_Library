package com.store.library.category;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    private Integer code;
    private String label;
    
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Category( Integer code, String label ) {
        super();
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode( Integer code ) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel( String label ) {
        this.label = label;
    }
    
    
    

}
