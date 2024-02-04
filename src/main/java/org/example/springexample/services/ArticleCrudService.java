package org.example.springexample.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.ArticleDto;
import org.example.springexample.entity.Article;
import org.example.springexample.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleCrudService implements CRUDService<ArticleDto> {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto getById(Integer id) {
        return mapToDto(articleRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<ArticleDto> getAll() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleCrudService::mapToDto)
                .toList();
    }

    @Override
    public void create(ArticleDto item) {
        articleRepository.save(mapToEntity(item));
    }

    @Override
    public void update(ArticleDto item) {
        articleRepository.save(mapToEntity(item));
    }

    @Override
    public void delete(Integer id) {
        articleRepository.deleteById(id);
    }

    public static Article mapToEntity(ArticleDto articleDto) {
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setCreationTime(articleDto.getCreationTime());
        article.setComments(articleDto.getComments()
                .stream()
                .map(CommentsCrudService::mapToEntity)
                .toList());
        return article;
    }

    public static ArticleDto mapToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setCreationTime(article.getCreationTime());
        articleDto.setComments(article.getComments()
                .stream()
                .map(CommentsCrudService::mapToDto)
                .toList());
        return articleDto;
    }
}