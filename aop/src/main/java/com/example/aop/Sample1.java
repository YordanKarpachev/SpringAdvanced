package com.example.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sample1 implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(Sample1.class);
    private SampleComponent sampleComponent;

    public Sample1(SampleComponent sampleComponent) {
        this.sampleComponent = sampleComponent;
    }

    @Override
    public void run(String... args) throws Exception {
 /*       sampleComponent.sayHallo();
        sampleComponent.echoSomething("I am demo");
        try {
            sampleComponent.doSomethingWrong();
        } catch (Exception e) {
            LOGGER.error("Ups an error with message {}", e.getMessage());
        }*/

        String s1 = "string1";
        String s2 = "string2";
        String result = sampleComponent.concatTwoString(s1,s2);
        LOGGER.info("Concatenating {} and {}. Result is {}", s1, s2 , result);

    }
}
