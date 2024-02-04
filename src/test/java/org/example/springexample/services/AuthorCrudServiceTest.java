package org.example.springexample.services;

import org.example.springexample.dto.AuthorDto;
import org.example.springexample.entity.Author;
import org.example.springexample.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorCrudServiceTest {
    private final AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
    private final AuthorCrudService authorCrudService = new AuthorCrudService(authorRepository);

    @Test
    @DisplayName("test getById Author")
    public void testGetByIdAuthor() {
        int authorId = 1;
        Author author = new Author();
        author.setId(authorId);
        author.setComments(List.of());
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorCrudService.getById(authorId);
        assertEquals(authorId, authorDto.getId());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    @DisplayName("test getAll Author")
    public void testGetAllAuthor() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(1);
        author.setComments(List.of());
        authors.add(author);
        when(authorRepository.findAll()).thenReturn(authors);
        Collection<AuthorDto> authorDtos = authorCrudService.getAll();
        assertEquals(authors.size(), authorDtos.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("test create Author")
    public void testCreateAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorCrudService.create(authorDto);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    @DisplayName("test update Author")
    public void testUpdateAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorCrudService.update(authorDto);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    @DisplayName("test deleteById Author")
    public void testDeleteByIdAuthor() {
        int authorId = 1;
        authorCrudService.delete(authorId);
        verify(authorRepository, times(1)).deleteById(authorId);
    }
}