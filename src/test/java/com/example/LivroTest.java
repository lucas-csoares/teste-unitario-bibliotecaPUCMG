package com.example;

import org.example.entities.Livro;
import org.example.enums.StatusLivro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LivroTest {

    @Test
    @DisplayName("Verifica se um livro pode ser criado corretamente")
    public void testCriarLivro() {
        new Livro("Dom Quixote", "Miguel de Cervantes");
    }

    @Test
    @DisplayName("Verifica se o status de um livro pode ser alterado para emprestado")
    public void testEmprestarLivro() {
        Livro livro = new Livro("Dom Quixote", "Miguel de Cervantes");
        livro.setStatus(StatusLivro.EMPRESTADO);
        assertThrows(Exception.class, livro::emprestarLivro);

        livro.setStatus(StatusLivro.DISPONIVEL);
        assertDoesNotThrow(livro::emprestarLivro);
    }

    @Test
    @DisplayName("Verifica se o status de um livro pode ser alterado para não emprestado")
    public void testRetornarLivro() {
        Livro livro = new Livro("Dom Quixote", "Miguel de Cervantes");
        livro.setStatus(StatusLivro.EMPRESTADO);
        assertDoesNotThrow(livro::retornarLivro);

        livro.setStatus(StatusLivro.DISPONIVEL);
        assertThrows(Exception.class, livro::retornarLivro);
    }
}
