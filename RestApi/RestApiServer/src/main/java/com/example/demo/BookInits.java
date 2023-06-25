package com.example.demo;

import com.example.demo.Models.entity.AuthorEntity;
import com.example.demo.Models.entity.BookEntity;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BookInits implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookInits(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DSadsadsa");

        if (bookRepository.count() == 0 && authorRepository.count() == 0) {
            initJovkov();
            initNikolaiHaitov();
            initDimitarTalev();
            initElinPelin();
            initVazov();
        }
    }

    private void initNikolaiHaitov() {
        initAuthor("Николай Хайтов",
                "Диви Разкази"
        );
    }

    private void initDimitarTalev() {
        initAuthor("Димитър Димов",
                "Тютюн"
        );
    }

    private void initElinPelin() {
        initAuthor("Елин Пелин",
                "Пижо и Пендо",
                "Ян Бибиян на луната",
                "Под манастирската лоза"
        );
    }

    private void initVazov() {
        initAuthor("Иван Вазов",
                "Пряпорец и Гусла",
                "Под Игото",
                "Тъгите на България"
        );
    }

    private void initJovkov() {

        initAuthor("Йордан Йовков",
                "Старопланински легенди",
                "Чифликът край границата");
    }

    private void initAuthor(String authorName, String... books) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorName);
        author = authorRepository.save(author);

        List<BookEntity> allBooks = new ArrayList<>();

        for (String book : books) {
            BookEntity aBook = new BookEntity();
            aBook.setAuthor(author);
            aBook.setTitle(book);
            aBook.setIsbn(UUID.randomUUID().toString());//random string, not real, dummy
            allBooks.add(aBook);
        }

        author.setBooks(allBooks);
        this.authorRepository.save(author);

        bookRepository.saveAll(allBooks);
    }
}

