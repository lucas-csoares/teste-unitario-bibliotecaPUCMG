package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Membro {
    private Integer id;
    private String nome;

    private List<Livro> livrosEmprestados;

    public Membro(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        livrosEmprestados = new ArrayList<>();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

    public void addLivrosEmprestados(Livro livro) {
        livrosEmprestados.add(livro);
    }

    public void devolverLivro(Livro livro) {
        livrosEmprestados.remove(livro);
    }
}
