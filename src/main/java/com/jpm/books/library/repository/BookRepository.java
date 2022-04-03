package com.jpm.books.library.repository;

import com.jpm.books.library.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    Optional<Books> findByIsbn(Long isbn);

    Books findByTitle(String title);

    Books findByAuthor(String author);
}
