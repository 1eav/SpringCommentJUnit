package org.example.springexample.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime creationTime;
    private List<CommentDto> comments;
}
