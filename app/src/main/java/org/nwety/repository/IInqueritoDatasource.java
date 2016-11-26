package org.nwety.repository;

import android.support.annotation.Nullable;
import org.nwety.model.Inquerito;

import java.util.List;

/**
 * Created by yassin on 11/26/16.
 */
public interface IInqueritoDatasource {
    List<Inquerito> getInqueritos();

    @Nullable
    Inquerito findInquerito(long id);
}
