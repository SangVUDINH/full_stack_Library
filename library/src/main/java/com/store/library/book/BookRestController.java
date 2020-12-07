package com.store.library.book;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.store.library.category.CategoryDTO;


@RestController
@RequestMapping("rest/book/api")
public class BookRestController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);
    
    @Autowired
    private BookServiceImpl bookService;
    
    @GetMapping("/searchByTitle")
    public ResponseEntity<List<BookDTO>> searchBookByTitle(@RequestParam("title") String title,
                UriComponentsBuilder uriComponenBuilder){
        
        List<Book> books = bookService.findBooksByTitleOrPartTitle( title );
        if (!CollectionUtils.isEmpty( books )) {
            //on retire les elements NULL afin d'eviter NullPointerException
            books.removeAll( Collections.singleton( null ) );
            List<BookDTO> bookDTOs= books.stream().map( book->{
                return mapBookToBookDTO(book);
            }).collect(Collectors.toList());
            
            return new ResponseEntity<List<BookDTO>>(bookDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<BookDTO>>(HttpStatus.NO_CONTENT);
    }

    private BookDTO mapBookToBookDTO( Book book ) {
        ModelMapper mapper = new ModelMapper();
        BookDTO bookDTO = mapper.map(book, BookDTO.class);
        if ( book.getCategory()!=null) {
            bookDTO.setCategory( new CategoryDTO(book.getCategory().getCode(), book.getCategory().getLabel()) );
        }
        System.out.println(bookDTO+"dsadsa");
        return bookDTO;        
    }


}
