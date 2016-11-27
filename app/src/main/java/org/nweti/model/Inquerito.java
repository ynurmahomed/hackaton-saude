package org.nweti.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class Inquerito {

    private long id;

    private Calendar inicio;

    private int sessao;

    private int sessoes;

    private String titulo;

    private List<Pergunta> perguntas;

    public Inquerito(long id, Calendar inicio, int sessoes, String titulo, List<Pergunta> perguntas) {
        this.id = id;
        this.inicio = inicio;
        this.sessao = 1;
        this.sessoes = sessoes;
        this.titulo = titulo;
        this.perguntas = perguntas;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public long getId() {
        return id;
    }

    public void setSessao(int sessao) {
        this.sessao = sessao;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public int getSessao() {
        return sessao;
    }
}
