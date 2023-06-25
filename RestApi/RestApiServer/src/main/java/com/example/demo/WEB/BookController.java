package com.example.demo.WEB;


import com.example.demo.Models.entity.DTO.BookDTO;
import com.example.demo.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> allBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @Operation(summary = "Get book by the given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "If the book was discovered.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "If the ID was incorrect."),
                    @ApiResponse(responseCode = "404", description = "If the book was not found.")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable("id") Long id) {
        Optional<BookDTO> theBook = this.bookService.findBookById(id);

        return theBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteById(@PathVariable("id") Long id) {
        this.bookService.deleteBookById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook, UriComponentsBuilder uriComponentsBuilder) {
        long newBookId = this.bookService.crateBook(newBook);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/api/books/{id}")
                .build(newBookId))
                .build();

    }

}
