package com.pinwheel.gofast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class, where project starts in.
 *
 * @author Listratenko Stanislav
 * @version 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
public class GoFast {

    /**
     * Main method for starting application. Runs spring application.
     *
     * @param args arguments of application.
     */
    public static void main(String[] args) {
        SpringApplication.run(GoFast.class, args);
    }
}
