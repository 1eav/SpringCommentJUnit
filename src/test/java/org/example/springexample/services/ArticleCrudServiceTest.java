package org.example.springexample.services;

import org.example.springexample.dto.ArticleDto;
import org.example.springexample.entity.Article;
import org.example.springexample.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleCrudServiceTest {
    @InjectMocks
    private ArticleCrudService articleCrudService;
    @Mock
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("test getById Article")
    public void testGetByIdArticle() {
        Integer articleId = 1;
        Article article = new Article();
        article.setId(articleId);
        article.setComments(List.of());
        when(articleRepository.findById((Integer) articleId)).thenReturn(Optional.of(article));
        ArticleDto articleDto = articleCrudService.getById((Integer) articleId);
        assertEquals(articleId, articleDto.getId());
        verify(articleRepository, times(1)).findById((Integer) articleId);
    }

    @Test
    @DisplayName("test getAll Article")
    public void testGetAllArticle() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setId(1);
        article.setComments(List.of());
        articles.add(article);
        when(articleRepository.findAll()).thenReturn(articles);
        Collection<ArticleDto> articleDto = articleCrudService.getAll();
        assertEquals(articles.size(), articleDto.size());
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("test create Article")
    public void testCreateArticle() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setComments(List.of());
        articleCrudService.create(articleDto);
        verify(articleRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("test update Article")
    public void testUpdateArticle() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setComments(List.of());
        articleCrudService.update(articleDto);
        verify(articleRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("test deleteById Article")
    public void testDeleteByIdArticle() {
        Integer articleId = 1;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(articleId);
        articleDto.setComments(List.of());
        articleCrudService.delete((Integer) articleId);
        verify(articleRepository, times(1)).deleteById((Integer) articleId);
    }
}