package com.store.library.category;

import java.util.ArrayList;
import java.util.List;

import com.store.library.book.Book;

public class CategoryDTO implements Comparable<CategoryDTO> {

    public CategoryDTO() {
    }
    
    public CategoryDTO(Integer integer, String label) {
        super();
        this.code = integer;
        this.label = label;
    }


    private Integer code;

    private String label;
    
    private List<Book> books = new ArrayList<>();
    
    

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks( List<Book> books ) {
        this.books = books;
    }

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

