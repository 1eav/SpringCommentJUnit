package org.example.springexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.springexample.dto.ArticleDto;
import org.example.springexample.services.ArticleCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleCrudService articleCrudService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Integer id) {
        ArticleDto articleDto = articleCrudService.getById(id);
        return ResponseEntity.ok(articleDto);
    }

    @GetMapping
    public ResponseEntity<Collection<ArticleDto>> getAllArticles() {
        Collection<ArticleDto> articles = articleCrudService.getAll();
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleDto articleDto) {
        articleCrudService.create(articleDto);
        return new ResponseEntity<>(articleDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ArticleDto> updateArticle(@RequestBody ArticleDto articleDto) {
        articleCrudService.update(articleDto);
        return new ResponseEntity<>(articleDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        articleCrudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}