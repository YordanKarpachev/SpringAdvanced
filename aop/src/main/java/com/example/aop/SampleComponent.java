package com.example.aop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleComponent {

    private final Logger LOGGER = LoggerFactory.getLogger(Sample1.class);


    public void sayHallo() {
        LOGGER.info("Hallo");
    }

    public void doSomethingWrong() {
        throw new NullPointerException("Upss Fehler!");
    }

    public void echoSomething(String something) {
        LOGGER.info("What i want to echo is {}", something);
    }

    public String concatTwoString(String s1, String s2) {
        return s1 + s2;
    }
}
