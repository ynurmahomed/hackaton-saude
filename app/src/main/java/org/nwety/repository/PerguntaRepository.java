package org.nwety.repository;

import org.nwety.model.Pergunta;

import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class PerguntaRepository implements IPerguntaDatasource {

    private PerguntaDatasourceMock datasource;

    public PerguntaRepository(PerguntaDatasourceMock datasource) {
        this.datasource = datasource;
    }

    public List<Pergunta> getPerguntas() {
        return datasource.getPerguntas();
    }
}