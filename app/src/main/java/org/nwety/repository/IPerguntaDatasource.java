package org.nwety.repository;

import org.nwety.model.Pergunta;

import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public interface IPerguntaDatasource {
    List<Pergunta> getPerguntas();
}
