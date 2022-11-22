package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;

import java.util.List;

public interface IMenuRowDao extends IDao<IMenuRow>{
    List<IMenuRow> readByMenuId(long id);
}
