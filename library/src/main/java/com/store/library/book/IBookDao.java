package com.store.library.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookDao extends JpaRepository<Book, Integer> {
    
 public Book findByIsbnIgnoreCase(String isbn);
    
    public List<Book> findByTitleLikeIgnoreCase(String title);
    
       @Query("SELECT b "
            + "FROM Book b "
            + "INNER JOIN b.category cat "
            + "WHERE cat.code = :code"
          )
    public List<Book> findByCategory(@Param("code") Integer codeCategory);

}
