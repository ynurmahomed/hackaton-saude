package org.nweti.model;

/**
 * Created by yassin on 11/26/16.
 */
public class Resposta {

    private Pergunta pergunta;

    private int valor;

    public Resposta(Pergunta pergunta, int valor) {
        this.pergunta = pergunta;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }
}
