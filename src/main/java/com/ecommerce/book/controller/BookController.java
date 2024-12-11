package com.ecommerce.book.controller;

import com.ecommerce.book.dto.CreateBookDTO;
import com.ecommerce.book.dto.ResponseBookDTO;
import com.ecommerce.book.dto.UpdateBookDTO;
import com.ecommerce.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books") // Updated to plural for REST consistency
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a new book
    @PostMapping
    public ResponseEntity<ResponseBookDTO> addBook(@RequestBody @Valid CreateBookDTO book) {
        ResponseBookDTO createdBook = bookService.addBook(book);
        return ResponseEntity.ok(createdBook);
    }

    // Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBookDTO> updateBook(
            @PathVariable int id,
            @RequestBody @Valid  UpdateBookDTO book) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        ResponseBookDTO updatedBook = bookService.updateBook(id, book);
        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDTO> getBookById(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        ResponseBookDTO book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<ResponseBookDTO>> getAllBooks() {
        List<ResponseBookDTO> books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(books);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        ResponseBookDTO book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
