package com.store.library.book;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.store.library.category.Category;
import com.store.library.category.CategoryDTO;
import com.store.library.category.CategoryServiceImpl;


@RestController
@RequestMapping("rest/book/api")
public class BookRestController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);
    
    @Autowired
    private BookServiceImpl bookService;
    
    @Autowired
    private CategoryServiceImpl categoryService;
    
    @GetMapping("/searchByTitle")
    public ResponseEntity<List<BookDTO>> searchBookByTitle(@RequestParam("title") String title,
                UriComponentsBuilder uriComponentBuilder){
        
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
    
    
    @GetMapping("/booksByCategory")
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@RequestParam("category_code") Integer cat_code){
        
        List<Book> books = bookService.getBooksByCategory( cat_code );
        
        if(!CollectionUtils.isEmpty(books)) {
            books.removeAll( Collections.singleton( null ) );
            
            List<BookDTO> bookDTOs= books.parallelStream().map( 
                    book ->{    return mapBookToBookDTO(book);
                }).collect(Collectors.toList());
            return new ResponseEntity<List<BookDTO>>(bookDTOs,HttpStatus.OK);
        }
        
        return new ResponseEntity<List<BookDTO>>(HttpStatus.NO_CONTENT);
    }
        
    
    @GetMapping("searchByIsbn")
    public ResponseEntity <BookDTO> searchBookByIsbn(@RequestParam("isbn") String isbn){
        
        Book book = bookService.findBookByIsbn( isbn );
        if(book !=null) {
            BookDTO bookDTO= mapBookToBookDTO(book);
            
            return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
        }
        
        return new ResponseEntity<BookDTO>(HttpStatus.NO_CONTENT);
    }
    
    
    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTORequest){
    
        Book existingBook = bookService.findBookByIsbn( bookDTORequest.getIsbn() );
        if(existingBook !=null) {
            return new ResponseEntity<BookDTO>(HttpStatus.CONFLICT);
        }
        
        //Category cat = categoryService.findById(bookDTORequest.getCategory().getCode());
        Book bookrequest = mapBookDTOToBook(bookDTORequest);
        
        Book book = bookService.saveBook( bookrequest );
        
        if(book !=null && book.getIdbook() !=null) {
            BookDTO bookDTO = mapBookToBookDTO(book);
            
            return new ResponseEntity <BookDTO>(bookDTO,HttpStatus.CREATED);
        }
        
        return new ResponseEntity<BookDTO>(HttpStatus.NOT_MODIFIED);
    }
    

    private Book mapBookDTOToBook( BookDTO bookDTORequest ) {
        ModelMapper mapper = new ModelMapper();
        Book book = mapper.map(bookDTORequest, Book.class);
        book.setCategory( new Category(bookDTORequest.getCategory().getCode(),"") );
        book.setRegisterdate( LocalDate.now() );        
        return book;
    }


    private BookDTO mapBookToBookDTO( Book book ) {
        ModelMapper mapper = new ModelMapper();
        BookDTO bookDTO = mapper.map(book, BookDTO.class);
        if ( book.getCategory()!=null) {
            bookDTO.setCategory( new CategoryDTO(book.getCategory().getCode(), book.getCategory().getLabel()) );
        }
        
        return bookDTO;        
    }

    

}
