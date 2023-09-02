package org.example.entities;

import org.example.enums.StatusLivro;

import java.util.List;
import java.util.NoSuchElementException;

public class Biblioteca {

    private List<Livro> acervo;

    private List<Membro> membrosCadastrados;

    public Biblioteca(List<Livro> acervo) {
        this.acervo = acervo;
    }

    public List<Livro> getAcervo() {
        return acervo;
    }

    public void setAcervo(List<Livro> acervo) {
        this.acervo = acervo;
    }

    public void addMembro(Membro membro) {
        membrosCadastrados.add(membro);
    }


    public void emprestarLivro(int livroId, int membroId) {
        Livro livro = autenticarLivro(livroId);
        Membro membro = autenticarMembro(membroId);
        verificarDisponibilidadeLivro(livro);


        membro.addLivrosEmprestados(livro);
        acervo.remove(livro);
        livro.setStatus(StatusLivro.EMPRESTADO);
    }


    public void registrarLivro(Livro livro) {
         if(acervo.contains(livro))
             throw new IllegalStateException("O livro já tem no acervo");
        acervo.add(livro);
    }

    public void retornarLivro(int livroId, int membroId) {
        Livro livro = autenticarLivro(livroId);
        Membro membro = autenticarMembro(membroId);
        verificarLivroNoAcervo(livro);

        if(!membro.getLivrosEmprestados().contains(livro)) throw new NoSuchElementException("O livro está emprestado " +
                "a outro membro");

        membro.devolverLivro(livro);
        livro.setStatus(StatusLivro.DISPONIVEL);
        acervo.add(livro);
    }

    private Livro autenticarLivro(int livroId) {
        Livro livro = acervo.stream().filter(x -> x.getId() == livroId).findFirst().orElseThrow(() ->
                new NullPointerException("Livro não existe no acervo"));
        return livro;
    }

    private Membro autenticarMembro(int membroId) {
        Membro membro = membrosCadastrados.stream().filter(x -> x.getId() == membroId).findFirst().orElseThrow(() ->
                new NoSuchElementException("Membro não cadastrado"));
        return membro;
    }

    private void verificarLivroNoAcervo(Livro livro) {
        if(livro.getStatus() == StatusLivro.DISPONIVEL)
            throw new IllegalStateException("O livro não foi emprestado");
    }

    private void verificarDisponibilidadeLivro(Livro livro) {
        if (livro.getStatus() != StatusLivro.DISPONIVEL) {
            throw new IllegalStateException("O livro não está disponível para empréstimo");
        }
    }


}
