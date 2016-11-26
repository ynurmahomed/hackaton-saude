package org.nwety.repository;

import org.nwety.model.Inquerito;

import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public class InqueritoRepository implements IInqueritoDatasource {

    private IInqueritoDatasource inqueritoDatasource;

    public InqueritoRepository(IInqueritoDatasource inqueritoDatasource) {
        this.inqueritoDatasource = inqueritoDatasource;
    }
    @Override
    public List<Inquerito> getInqueritos() {
        return inqueritoDatasource.getInqueritos();
    }

    @Override
    public Inquerito findInquerito(long id) {
        return inqueritoDatasource.findInquerito(id);
    }
}
