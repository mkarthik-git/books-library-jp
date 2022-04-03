package com.jpm.books.library.controller;

import com.jpm.books.library.entity.Books;
import com.jpm.books.library.request.BookRequest;
import com.jpm.books.library.response.RestMessageResponse;
import com.jpm.books.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@EnableAutoConfiguration
@RequestMapping(value={"/api"}, produces = {APPLICATION_JSON_VALUE})
@Api(value="Book Service", description="Book Management Service")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value="/books/list",produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all Books List", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all books"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request") })
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> findAllBookResponse = bookService.findAllBooks();
        return new ResponseEntity<>(findAllBookResponse, HttpStatus.OK);
    }

    @GetMapping(value="/books/isbn/{isbn}",produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a Book by ISBN ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a book by ISBN"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request") })
    public ResponseEntity<Books> getBookByISBN(@PathVariable Long isbn) {
        Optional<Books> findByIsbnBookResponse = bookService.findBookByIsbn(isbn);
        if(findByIsbnBookResponse.isPresent()){
            Books bookResponse = findByIsbnBookResponse.get();
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/books/id/{id}",produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a Book by Id ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a book by Id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request") })
    public ResponseEntity<Optional<Books>> getBookById(@PathVariable Long id) {

        return Optional
                .ofNullable(bookService.findBookById(id))
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/books/title/{title}")
    @ApiOperation(value = "Get a Book Record by Title", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a book by its Title"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request") })
    public ResponseEntity<Books> getBookByTitle(@PathVariable String title) {
        Books bookTitleResponse = bookService.findBookByTitle(title);
        return new ResponseEntity<>(bookTitleResponse, HttpStatus.OK);
    }

    @GetMapping("/books/author/{author}")
    @ApiOperation(value = "Get a Book Record by Author", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a book by its Author"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request") })
    public ResponseEntity<Books> getBookByAuthor(@PathVariable String author) {
        Books bookAuthorResponse = bookService.findBookByAuthor(author);
        return new ResponseEntity<>(bookAuthorResponse, HttpStatus.OK);
    }

    @PostMapping("/books/add")
    @ApiOperation(value = "Create a new book item", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a new book item"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")})
    public ResponseEntity<RestMessageResponse> addBook(@RequestBody BookRequest bookRequest) {
        RestMessageResponse newBookResponse = bookService.createBookRecord(bookRequest);
        return new ResponseEntity<>(newBookResponse, HttpStatus.CREATED);
    }

    @PutMapping("/books/update/{isbn}")
    @ApiOperation(value = "Update an existing Book Record item", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated an existing book item"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")})
    public ResponseEntity<Books> updateBook(@PathVariable Long isbn, @RequestBody BookRequest bookRequest) {
        Optional<Books> updateBookResponse = bookService.updateBookRecord(isbn, bookRequest);
        if(updateBookResponse.isPresent()){
            Books bookResponse = updateBookResponse.get();
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books/delete/{isbn}")
    @ApiOperation(value = "Deletes specific book with the supplied isbn", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted a Book record by ISBN"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")})
    public ResponseEntity<?> deleteBook(@PathVariable("isbn") Long isbn) {
        bookService.deleteBookRecord(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
