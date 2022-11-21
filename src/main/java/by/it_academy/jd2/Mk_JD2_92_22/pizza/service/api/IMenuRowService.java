package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuRowDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;

import java.time.LocalDateTime;

public interface IMenuRowService extends IService<IMenuRow> {

    IMenuRow create(IMenuRowDTO menuRowDTO);

    IMenuRow update(long id, LocalDateTime dtUpdate,IMenuRowDTO menuRowDTO);

}
