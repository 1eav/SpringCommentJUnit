package org.example.springexample.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long rating;
    private List<CommentDto> comments = List.of();
}