package com.example.RestClient;

import com.example.RestClient.dto.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientDemo implements CommandLineRunner {

    private static final String API_URL = "http://localhost:8080/api/books";

    private static final Logger logger = LoggerFactory.getLogger(RestClientDemo.API_URL);

    private final RestTemplate restTemplate;

    public RestClientDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        ResponseEntity<BookDTO[]> allBooksResponse = restTemplate.getForEntity(API_URL, BookDTO[].class);

        if(allBooksResponse.hasBody()){

            BookDTO[] body = allBooksResponse.getBody();

            for (BookDTO bookDTO : body) {
                logger.info("Book {}", bookDTO);
            }

        }

    }
}
