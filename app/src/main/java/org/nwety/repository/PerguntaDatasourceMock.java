package org.nwety.repository;

import org.nwety.model.Pergunta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class PerguntaDatasourceMock implements IPerguntaDatasource {

    private List<Pergunta> perguntas;

    public PerguntaDatasourceMock() {
        Pergunta p1 = new Pergunta("Tu és forte suficiente capaz de superar os desafios da vida (o)");
        Pergunta p2 = new Pergunta("Tu és muito fraca (o)");
        Pergunta p3 = new Pergunta("Tu és capaz de enfrentar as dificuldades da vida");
        Pergunta p4 = new Pergunta("Tu és bem sucedida (o) ");
        Pergunta p5 = new Pergunta("Muitas vezes sente que não acerta nada");
        Pergunta p6 = new Pergunta("És competente para lidar eficazmente com o mundo real ");
        Pergunta p7 = new Pergunta("Muitas vezes te sentes um fracasso");
        Pergunta p8 = new Pergunta("Geralmente sente que pode lidar com os problemas típicos que surgem na vida");

        this.perguntas = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }
}
