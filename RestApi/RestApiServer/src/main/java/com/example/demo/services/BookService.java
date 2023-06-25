package com.example.demo.services;


import com.example.demo.Models.entity.AuthorEntity;
import com.example.demo.Models.entity.BookEntity;
import com.example.demo.Models.entity.DTO.AuthorDTO;
import com.example.demo.Models.entity.DTO.BookDTO;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().
                stream().
                map(this::map).
                toList();

    }

    private BookDTO map(BookEntity bookEntity) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(bookEntity.getAuthor().getName());
        return new BookDTO(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn(), authorDTO);
    }

    public Optional<BookDTO> findBookById(Long bookId) {
        return this.bookRepository.findById(bookId).map(this::map);

    }

    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    public long crateBook(BookDTO newBook) {
        String authorName = newBook.getAuthor().getName();
        Optional<AuthorEntity> author = this.authorRepository.findAuthorEntityByName(authorName);

        BookEntity newBookEntity = new BookEntity();
        newBookEntity.setIsbn(newBook.getIsbn());
        newBookEntity.setTitle(newBook.getTitle());
        newBookEntity.setAuthor(author.orElseGet(() -> createNewAuthor(authorName)));

        return this.bookRepository.save(newBookEntity).getId();
    }

    private AuthorEntity createNewAuthor(String authorName) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorName);
        return this.authorRepository.save(author);
    }
}
