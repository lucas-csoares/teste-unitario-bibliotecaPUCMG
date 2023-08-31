package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> acervo;

    public Biblioteca(List<Livro> acervo) {
        this.acervo = acervo;
    }

    public List<Livro> getAcervo() {
        return acervo;
    }

    public void setAcervo(List<Livro> acervo) {
        this.acervo = acervo;
    }




}
