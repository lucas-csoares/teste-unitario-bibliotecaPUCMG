package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.entities.Livro;
import org.example.entities.Membro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MembroTest {

    @Test
    @DisplayName("Verifica se um membro pode ser criado corretamente")
    public void testCriarMembro() {
        new Membro("Gustavo");
    }

    @Test
    @DisplayName("Verifica se um membro pode pegar um livro emprestado corretamente")
    public void testMembroPegaEmprestado() {
        Membro membro = new Membro("Joao");
        Livro livro1 = new Livro("bla", "aaaaaa");
        membro.addLivrosEmprestados(livro1);
        Livro livro2 = membro.getLivrosEmprestados().stream().filter(x -> x.getTitulo() == livro1.getTitulo())
                .findFirst().orElse(null);

        assertEquals(livro1, livro2);
    }

    @Test
    @DisplayName("Verifica se um membro pode pegar um livro emprestado corretamente")
    public void testMembroRetornaLivro() {
        Membro membro = new Membro("Joao");
        Livro livro1 = new Livro("bla", "aaaaaa");
        membro.addLivrosEmprestados(livro1);
        membro.devolverLivro(livro1);
        Livro livro2 = membro.getLivrosEmprestados().stream().filter(x -> x.getTitulo() == livro1.getTitulo())
                .findFirst().orElse(null);

        assertEquals(livro2, null);
    }

}
