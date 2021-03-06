package com.store.library.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.library.book.Book;

public interface ICategoryDao extends JpaRepository<Category, Integer> {

}
