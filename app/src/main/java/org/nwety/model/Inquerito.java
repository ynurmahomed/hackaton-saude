package org.nwety.model;

import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class Inquerito {

    private String titulo;

    private List<Pergunta> perguntas;

    public Inquerito(String titulo, List<Pergunta> perguntas) {
        this.titulo = titulo;
        this.perguntas = perguntas;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }
}
