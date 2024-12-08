package com.ecommerce.book.controller;

import com.ecommerce.book.dto.CreateBookDTO;
import com.ecommerce.book.dto.ResponseBookDTO;
import com.ecommerce.book.dto.ResponseCategoryDTO;
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
//        ResponseBookDTO responseBookDTO = bookService.addBook(book);
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/changebook/{id}")
    public ResponseEntity<ResponseBookDTO> changeBook (@PathVariable int id, @RequestBody UpdateBookDTO book){
        ResponseBookDTO bookDTO = bookService.updateBook(id, book);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/getbook/{id}")
    public ResponseEntity<Optional<ResponseBookDTO>> getBookById(@PathVariable int id) {
        Optional<ResponseBookDTO> bookDTO = Optional.ofNullable(bookService.getBookById(id));
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<ResponseBookDTO>> getAllBooks(){
        List<ResponseBookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/deletebook/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
