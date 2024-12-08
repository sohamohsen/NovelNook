package com.ecommerce.book.service;

import com.ecommerce.book.dto.CreateBookDTO;
import com.ecommerce.book.dto.ResponseBookDTO;
import com.ecommerce.book.dto.ResponseCategoryDTO;
import com.ecommerce.book.dto.UpdateBookDTO;
import com.ecommerce.book.entity.Book;
import com.ecommerce.book.entity.Category;
import com.ecommerce.book.mapper.BookMapper;
import com.ecommerce.book.mapper.CategoryMapper;
import com.ecommerce.book.repository.BookRepo;
import com.ecommerce.book.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseBookDTO updateBook(int id, UpdateBookDTO book) {
        if (id <= 0) {
            throw new IllegalArgumentException("Book ID must be greater than zero.");
        }

        // Find the book by ID
        Book bookToUpdate = bookRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + id));

        // Fetch the Category entity
        Category category = categoryRepo.findById(book.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + book.getCategoryId()));

        // Update the book fields
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPrice(book.getPrice());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setPublishYear(book.getPublishYear());
        bookToUpdate.setCategory(category); // Set the Category entity

        // Save the updated book
        Book updatedBook = bookRepo.save(bookToUpdate);

        // Map and return the updated book as DTO
        return BookMapper.instance.toDTO(updatedBook);
    }



    public List<ResponseBookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return BookMapper.instance.toDTOList(books);
    }

    public ResponseBookDTO addBook(CreateBookDTO book) {
        if (book.getTitle() == null || book.getAuthor() == null || book.getPrice()<1 || book.getStock()<1) {
            throw new  IllegalArgumentException("Book name cannot be null or empty");
        }
        if (book.getCategoryId() == 0)
        {
            book.setCategoryId(13);
        }
        Book newBook = BookMapper.instance.toEntity(book);
        return BookMapper.instance.toDTO(bookRepo.save(newBook));
    }

    public ResponseBookDTO getBookById(int id) {
        Optional<Book> book = bookRepo.findById(id);
        // Handle the case when category is not found
        if (book.isEmpty()) {
            throw new IllegalArgumentException("Category with ID " + id + " not found");
        }
        return BookMapper.instance.toDTO(book.get());
    }

    public void deleteBook(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Book id cannot be null or empty");
        }
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            bookRepo.deleteById(id);
        }
    }
}
