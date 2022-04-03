package com.jpm.books.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SpringBootApplication(scanBasePackages = {"com.jpm.books.library"})
//@EnableJpaRepositories("com.jpm.books.library.repository")
@SpringBootApplication
@EntityScan(basePackages = "com.jpm.books.library.entity")
@EnableJpaRepositories(basePackages = "com.jpm.books.library.repository")
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}

