package com.jpm.books.library.service.impl;

import com.jpm.books.library.entity.Books;
import com.jpm.books.library.exception.ResourceNotFoundException;
import com.jpm.books.library.repository.BookRepository;
import com.jpm.books.library.request.BookRequest;
import com.jpm.books.library.response.RestMessageResponse;
import com.jpm.books.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Books> findAllBooks() {
        List<Books> booksList = bookRepository.findAll();
        if(booksList.size() > 0) {
            return booksList;
        } else {
            return new ArrayList<Books>();
        }

    }

    @Override
    public Books findBookByTitle(String title) {
        Books bookByTitle = bookRepository.findByTitle(title);
        return bookByTitle;
    }

    @Override
    public Books findBookByAuthor(String author) {
        Books bookByAuthor = bookRepository.findByAuthor(author);
        return bookByAuthor;
    }

    @Override
    public Optional<Books> findBookByIsbn(Long isbn) {
        return Optional.ofNullable(bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book not found with ID %d", isbn))));
    }

    @Override
    public Optional<Books> findBookById(Long id) {
        Optional<Books> bookObj = bookRepository.findById(id);
        if(bookObj.isPresent())
        {
            Books newEntity = bookObj.get();
            return Optional.of(newEntity);
        } else if (bookObj == null) {
            throw new RuntimeException("Book not found");
        }
        return bookObj;
    }

    @Override
    public RestMessageResponse createBookRecord(BookRequest bookRequest) {
        Books newBook = new Books();
        newBook.setIsbn(bookRequest.getIsbn());
        newBook.setTitle(bookRequest.getTitle());
        newBook.setAuthor(bookRequest.getAuthor());
        newBook.setDescription(bookRequest.getDescription());
        newBook.setTags(bookRequest.getTags());
        bookRepository.save(newBook);
        return new RestMessageResponse("New Book object with Id created successfully");
    }

    /*public BooksEntity createOrUpdateBook(BooksEntity entity) throws ResourceNotFoundException
    {
        Optional<BooksEntity> bookObj = bookRepository.findById(entity.getIsbn());

        if(bookObj.isPresent())
        {
            BooksEntity newEntity = bookObj.get();
            newEntity.setTitle(entity.getTitle());
            newEntity.setAuthor(entity.getAuthor());
            newEntity.setDescription(entity.getDescription());
            newEntity = bookRepository.save(newEntity);

            return newEntity;
        } else {
            entity = bookRepository.save(entity);

            return entity;
        }
    } */

    @Override
    public Optional<Books> updateBookRecord(Long bookIsbn, BookRequest bookRequest) {
        Optional<Books> bookObj = bookRepository.findById(bookIsbn);
        if (!bookObj.isPresent()){
            throw new ResourceNotFoundException(String.format("Book not found with ID %d to update record", bookIsbn));
        }
        else
        bookObj.get().setTitle(bookRequest.getTitle());
        bookObj.get().setAuthor(bookRequest.getAuthor());
        bookObj.get().setDescription(bookRequest.getDescription());
        //bookObj.get().setTags(bookRequest.getTags());
        bookRepository.save(bookObj.get());
        return bookObj;

    }

    @Override
    public void deleteBookRecord(Long isbn) {
        /*final Books book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book not found with ID %d", isbn)));*/

        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
        } else {
            throw new ResourceNotFoundException(String.format("Book not found with ID %d", isbn));
        }
    }

}
