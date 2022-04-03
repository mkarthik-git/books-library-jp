package com.jpm.books.library.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_isbn")
    private Long isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @ElementCollection
    private List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "[id=" + id +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' + "]" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id) && Objects.equals(isbn, books.isbn) && Objects.equals(title, books.title) &&
                Objects.equals(author, books.author) && Objects.equals(description, books.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, description);
    }
}
