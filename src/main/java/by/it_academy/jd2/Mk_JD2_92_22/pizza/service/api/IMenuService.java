package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;

import java.time.LocalDateTime;


public interface IMenuService extends IService<IMenu> {
    IMenu create(IMenuDTO menuDTO);

    IMenu update(long id, LocalDateTime dtUpdate, IMenuDTO menuDTO);
}
