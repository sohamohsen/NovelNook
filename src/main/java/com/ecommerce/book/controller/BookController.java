package com.ecommerce.book.controller;

import com.ecommerce.book.dto.CreateBookDTO;
import com.ecommerce.book.dto.ResponseBookDTO;
import com.ecommerce.book.dto.UpdateBookDTO;
import com.ecommerce.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<ResponseBookDTO> addBook (@RequestBody CreateBookDTO book){
        if (book == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/updater/{id}")
    public ResponseEntity<ResponseBookDTO> changeBook (@PathVariable int id, @RequestBody UpdateBookDTO book){
        if (book == null){
            return ResponseEntity.noContent().build();
        }
        ResponseBookDTO bookDTO = bookService.updateBook(id, book);
        if (bookDTO == null){
            return ResponseEntity.badRequest().build();
        }else if(bookService.getBookById((int) bookDTO.getId()) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Optional<ResponseBookDTO>> getBookById(@PathVariable int id) {
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        Optional<ResponseBookDTO> bookDTO = Optional.ofNullable(bookService.getBookById(id));
        if(bookDTO.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<ResponseBookDTO>> getAllBooks(){
        List<ResponseBookDTO> books = bookService.getAllBooks();
        if(books == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if(id <1){
            return ResponseEntity.noContent().build();
        }else if(bookService.getBookById(id) == null ){
            return ResponseEntity.notFound().build();
        }
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
