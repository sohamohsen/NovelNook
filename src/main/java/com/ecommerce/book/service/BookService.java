package com.ecommerce.book.service;

import com.ecommerce.book.dto.*;
import com.ecommerce.book.entity.Book;
import com.ecommerce.book.entity.User;
import com.ecommerce.book.mapper.BookMapper;
import com.ecommerce.book.mapper.UserMapper;
import com.ecommerce.book.repository.BookRepo;
import com.ecommerce.book.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CategoryRepo categoryRepo;

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

    public ResponseBookDTO updateBook(int id, UpdateBookDTO book) {
        if (id <= 0) {
            throw new IllegalArgumentException("There is no data");
        }
        if (book == null) {
            throw new IllegalArgumentException("There is no data");
        }

        Book existingBook = bookRepo.findById(id).orElse(null);
        if (existingBook == null) {
            throw new IllegalArgumentException("There is no book with this id");
        }

        UpdateBookDTO updateBook = BookMapper.instance.toUpdateDTO(existingBook);

        if (updateBook == null) {
            throw new IllegalArgumentException("There is no book with this id");
        } else {
            // Check and handle title update
            if (book.getTitle() == null || book.getTitle().isEmpty() || book.getTitle().equals(updateBook.getTitle())) {
                System.out.println("Book title is either empty or unchanged, skipping update.");
            } else {
                updateBook.setTitle(book.getTitle());
            }

            // Check and handle author update
            if (book.getAuthor() == null || book.getAuthor().isEmpty() || book.getAuthor().equals(updateBook.getAuthor())) {
                System.out.println("Book author is either empty or unchanged, skipping update.");
            } else {
                updateBook.setAuthor(book.getAuthor());
            }

            // Check and handle description update
            if (book.getDescription() == null || book.getDescription().isEmpty() || book.getDescription().equals(updateBook.getDescription())) {
                System.out.println("Book description is either empty or unchanged, skipping update.");
            } else {
                updateBook.setDescription(book.getDescription());
            }

            // Check and handle price update
            if (book.getPrice() == 0 || book.getPrice() == updateBook.getPrice()) {
                System.out.println("Book price is either zero or unchanged, skipping update.");
            } else {
                updateBook.setPrice(book.getPrice());
            }

            // Check and handle stock update
            if (book.getStock() == 0 || book.getStock() == updateBook.getStock()) {
                System.out.println("Book stock is either zero or unchanged, skipping update.");
            } else {
                updateBook.setStock(book.getStock());
            }

            // Check and handle cover_image_url update
            if (book.getCover_image_url() == null || book.getCover_image_url().isEmpty() || book.getCover_image_url().equals(updateBook.getCover_image_url())) {
                System.out.println("Book cover image URL is either empty or unchanged, skipping update.");
            } else {
                updateBook.setCover_image_url(book.getCover_image_url());
            }

            // Check and handle publishYear update
            if (book.getPublishYear() == 0 || book.getPublishYear() == updateBook.getPublishYear()) {
                System.out.println("Book publish year is either zero or unchanged, skipping update.");
            } else {
                updateBook.setPublishYear(book.getPublishYear());
            }

            // Check and handle publisher update
            if (book.getPublisher() == null || book.getPublisher().isEmpty() || book.getPublisher().equals(updateBook.getPublisher())) {
                System.out.println("Book publisher is either empty or unchanged, skipping update.");
            } else {
                updateBook.setPublisher(book.getPublisher());
            }

            // Check and handle categoryId update
            if (book.getCategoryId() == 0 || book.getCategoryId() == updateBook.getCategoryId()) {
                System.out.println("Book category ID is either zero or unchanged, skipping update.");
            } else {
                updateBook.setCategoryId(book.getCategoryId());
            }
        }

        // Now ensure the existing book's ID is set in the entity
        Book updateEntity = BookMapper.instance.toUpdateEntity(updateBook);
        updateEntity.setId(id);  // Ensure the ID is set to the existing book's ID

        return BookMapper.instance.toDTO(bookRepo.save(updateEntity));
    }

    public List<ResponseBookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return BookMapper.instance.toDTOList(books);
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