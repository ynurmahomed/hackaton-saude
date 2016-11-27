package org.nweti.repository;

import org.nweti.model.Inquerito;
import org.nweti.model.Pergunta;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class InqueritoDataSourceMock implements IInqueritoDatasource {

    private List<Inquerito> inqueritos;

    public InqueritoDataSourceMock() {
        List<Pergunta> perguntas = Arrays.asList(new Pergunta("Tu és forte suficiente capaz de superar os desafios da vida (o)"),
                new Pergunta("Tu és muito fraca (o)"),
                new Pergunta("Tu és capaz de enfrentar as dificuldades da vida"),
                new Pergunta("Tu és bem sucedida (o) "),
                new Pergunta("Muitas vezes sente que não acerta nada"),
                new Pergunta("És competente para lidar eficazmente com o mundo real "),
                new Pergunta("Muitas vezes te sentes um fracasso"),
                new Pergunta("Geralmente sente que pode lidar com os problemas típicos que surgem na vida"));

        this.inqueritos = Collections.singletonList(new Inquerito(1L, Calendar.getInstance(), 9, "Inquérito Master", perguntas));
    }

    @Override
    public List<Inquerito> getInqueritos() {
        return inqueritos;
    }

    @Override
    public Inquerito findInquerito(long id) {
        for (Inquerito i : inqueritos) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }
}
