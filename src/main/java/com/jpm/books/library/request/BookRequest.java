package com.jpm.books.library.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Getter
@Setter
public class BookRequest {
    @NotBlank
    private Long isbn;
    private String description;
    private String title;
    private String author;
    private List<String> tags;
}
