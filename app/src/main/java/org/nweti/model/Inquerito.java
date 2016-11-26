package org.nweti.model;

import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class Inquerito {

    private long id;

    private String titulo;

    private List<Pergunta> perguntas;

    public Inquerito(long id, String titulo, List<Pergunta> perguntas) {
        this.id = id;
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
}
