package com.example;

import org.example.entities.Biblioteca;
import org.example.entities.Livro;
import org.example.entities.Membro;
import org.example.enums.StatusLivro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    private Livro livro1 = new Livro("Dom Quixote", "Miguel de Cervantes");
    private Livro livro2 = new Livro("Mr. Mercedes", "Stephen King");

    private Livro livro3 = new Livro("2001: uma odisséia no espaço", "Arthur C. Clarke");
    private Membro membro1 = new Membro("Vitor Lagares");
    private Membro membro2 = new Membro("Guilherme Lage");
    private Membro membro3 = new Membro("Lucas Cabral");



    @BeforeEach
    public void configurarBiblioteca() {
        List<Livro> listaParaAcervo = new ArrayList<>();
        listaParaAcervo.add(livro1);
        listaParaAcervo.add(livro2);
        biblioteca = new Biblioteca(listaParaAcervo);
    }

    @Test
    @DisplayName("Validação do registro de livro no acervo")
    public void testRegistrarLivro() {
        assertDoesNotThrow(() -> biblioteca.registrarLivro(livro3));
        assertThrows(Exception.class, () -> biblioteca.registrarLivro(livro1));
    }



    @Test
    @DisplayName("Validação do empréstimo de livro no acervo")
    public void testEmprestarLivro() {
        biblioteca.addMembro(membro1);
        biblioteca.addMembro(membro2);
        assertDoesNotThrow(() -> biblioteca.emprestarLivro(livro1.getId(), membro1.getId()));

        assertThrows(Exception.class, () -> biblioteca.emprestarLivro(livro3.getId(), membro1.getId()));

        assertThrows(Exception.class, () -> biblioteca.emprestarLivro(livro1.getId(), membro3.getId()));

    }


    @Test
    @DisplayName("Invalidar empréstimo de livro que já foi emprestado")
    public void testEmprestarLivroNaoDisponivel() {
        biblioteca.addMembro(membro1);
        biblioteca.addMembro(membro2);
        livro1.setStatus(StatusLivro.EMPRESTADO);
        assertThrows(Exception.class, () -> biblioteca.emprestarLivro(livro1.getId(), membro2.getId()));
    }

    @Test
    @DisplayName("Validação de retorno do empréstimo")
    public void testRetornarLivro() {
        biblioteca.addMembro(membro1);
        biblioteca.addMembro(membro2);

        try {
            biblioteca.emprestarLivro(livro1.getId(), membro1.getId());
        } catch (Exception e) {
        }

        assertDoesNotThrow(() -> biblioteca.retornarLivro(livro1.getId(), membro1.getId()));
    }




    @Test
    @DisplayName("Invalidar retorno feito por um membro que não pegou emprestado")
    public void testRetornarLivroNaoDisponivel() {
        biblioteca.addMembro(membro1);
        biblioteca.addMembro(membro2);
        try {
            biblioteca.emprestarLivro(livro1.getId(), membro1.getId());
        } catch (Exception e) {
        }

        assertThrows(Exception.class, () -> biblioteca.retornarLivro(livro1.getId(), membro2.getId()));
    }









}
