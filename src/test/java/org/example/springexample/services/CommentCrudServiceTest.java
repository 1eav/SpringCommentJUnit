package org.example.springexample.services;

import org.example.springexample.dto.CommentDto;
import org.example.springexample.entity.Article;
import org.example.springexample.entity.Author;
import org.example.springexample.entity.Comment;
import org.example.springexample.repository.AuthorRepository;
import org.example.springexample.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentCrudServiceTest {
    @InjectMocks
    private CommentsCrudService commentsCrudService;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private AuthorRepository authorRepository;

    @Value("${comment.length.max}")
    private Integer maxLength;

    @Test
    @DisplayName("test getById Comment")
    public void testGetByIdComment() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setAuthor(new Author());
        comment.setArticle(new Article());
        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        CommentDto commentDto = commentsCrudService.getById(1);
        assertEquals(comment.getId(), commentDto.getId());
        verify(commentRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("test getAll Comment")
    public void testGetAllComment() {
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setId(1);
        comment.setAuthor(new Author());
        comment.setArticle(new Article());
        comments.add(comment);
        when(commentRepository.findAll()).thenReturn(comments);
        Collection<CommentDto> commentDtos = commentsCrudService.getAll();
        assertEquals(comments.size(), commentDtos.size());
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("test create Comment")
    public void testCreateComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(1);
        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        commentsCrudService.create(commentDto);
        verify(commentRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("test update Comment")
    public void testUpdateComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(1);
        commentDto.setText("Text");
        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        ReflectionTestUtils.setField(commentsCrudService, "maxLength", 10);
        commentsCrudService.update(commentDto);
        verify(commentRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("test update RuntimeException Comment")
    public void testUpdateRuntimeExceptionComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(1);
        commentDto.setText("TextTextTextTextTextTextTextTextTextText");
        ReflectionTestUtils.setField(commentsCrudService, "maxLength", 10);
        assertThrows(RuntimeException.class, () -> commentsCrudService.update(commentDto));
    }

    @Test
    @DisplayName("test deleteById Comment")
    public void testDeleteByIdComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);
        commentsCrudService.delete(1);
        verify(commentRepository, times(1)).deleteById(1);
    }
}