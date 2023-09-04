package org.example.entities;

import org.example.enums.StatusLivro;

import java.util.ArrayList;
import java.util.List;


public class Biblioteca {

    private List<Livro> acervo;

    private List<Membro> membrosCadastrados;

    public Biblioteca(List<Livro> acervo) {

        this.acervo = acervo;

    membrosCadastrados = new ArrayList<>();
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


    public void emprestarLivro(int livroId, int membroId) throws Exception {
        Livro livro = autenticarLivro(livroId);
        Membro membro = autenticarMembro(membroId);
        verificarDisponibilidadeLivro(livro);


        membro.addLivrosEmprestados(livro);
        livro.emprestarLivro();
    }


    public void registrarLivro(Livro livro) throws Exception{
        if (acervo.contains(livro))
            throw new Exception("O livro já tem no acervo");
        acervo.add(livro);
    }

    public void retornarLivro(int livroId, int membroId) throws Exception {
        Livro livro = autenticarLivro(livroId);
        Membro membro = autenticarMembro(membroId);
        verificarLivroNoAcervo(livro);

        if (!membro.getLivrosEmprestados().contains(livro))
            throw new Exception("O livro está emprestado " +
                    "a outro membro");

        membro.devolverLivro(livro);
        livro.retornarLivro();
    }

    private Livro autenticarLivro(int livroId) throws Exception {
        Livro livro = acervo.stream().filter(x -> x.getId() == livroId).findFirst().orElseThrow(() ->
                new Exception("Livro não existe no acervo"));
        return livro;
    }

    private Membro autenticarMembro(int membroId) throws Exception{
        Membro membro = membrosCadastrados.stream().filter(x -> x.getId() == membroId).findFirst().orElseThrow(() ->
                new Exception("Membro não cadastrado"));
        return membro;
    }

    private void verificarLivroNoAcervo(Livro livro) throws Exception{
        if (livro.getStatus() == StatusLivro.DISPONIVEL)
            throw new Exception("O livro não foi emprestado");
    }

    private void verificarDisponibilidadeLivro(Livro livro) throws Exception{
        if (livro.getStatus() != StatusLivro.DISPONIVEL) {
            throw new Exception("O livro não está disponível para empréstimo");
        }
    }


}
