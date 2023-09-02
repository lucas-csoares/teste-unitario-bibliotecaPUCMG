package org.example.entities;

import org.example.enums.StatusLivro;

public class Livro {
    private static Integer proximoId = 1;
    private Integer id;
    private String titulo;
    private String autor;
    private StatusLivro status;

    public Livro(String titulo, String autor) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.autor = autor;
        status = StatusLivro.DISPONIVEL;
    }

    public void emprestarLivro() throws Exception {
        if (this.getStatus() == StatusLivro.DISPONIVEL)
            this.setStatus(StatusLivro.EMPRESTADO);
        else
            throw new Exception("Livro ja emprestado!");
    }

    public void retornarLivro() throws Exception {
        if (this.getStatus() == StatusLivro.EMPRESTADO)
            this.setStatus(StatusLivro.DISPONIVEL);
        else
            throw new Exception("Livro nao esta emprestado!");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }
}
