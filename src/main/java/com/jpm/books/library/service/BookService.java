package com.jpm.books.library.service;
import com.jpm.books.library.entity.Books;
import com.jpm.books.library.request.BookRequest;
import com.jpm.books.library.response.RestMessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {

    List<Books> findAllBooks();

    Optional<Books> findBookById(Long id);

    Optional<Books> findBookByIsbn(Long id);

    RestMessageResponse createBookRecord(BookRequest book);

    Optional<Books> updateBookRecord(Long id, BookRequest book);

    Books findBookByTitle(String title);

    Books findBookByAuthor(String title);

    void deleteBookRecord(Long isbn);

}
