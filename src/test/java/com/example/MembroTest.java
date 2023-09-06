package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.entities.Livro;
import org.example.entities.Membro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MembroTest {

    private Membro membro;
    private Livro livro1;

    @BeforeEach
    public void configurarLivroMembro() {
        livro1 = new Livro("A Arte da Guerra", "Sun Tzu");
        membro = new Membro("Joao");
        membro.addLivrosEmprestados(livro1);
    }

    @Test
    @DisplayName("Verifica se um membro pode ser criado corretamente")
    public void testCriarMembro() {
        new Membro("Gustavo");
    }

    @Test
    @DisplayName("Verifica se um membro pode pegar um livro emprestado corretamente")
    public void testMembroPegaEmprestado() {
        
        Livro livro2 = membro.getLivrosEmprestados().stream().filter(x -> x.getTitulo() == livro1.getTitulo())
                .findFirst().orElse(null);

        assertEquals(livro1, livro2);
    }

    @Test
    @DisplayName("Verifica se um membro pode pegar um livro emprestado corretamente")
    public void testMembroRetornaLivro() {
        membro.devolverLivro(livro1);
        Livro livro2 = membro.getLivrosEmprestados().stream().filter(x -> x.getTitulo() == livro1.getTitulo())
                .findFirst().orElse(null);

        assertEquals(livro2, null);
    }

}
