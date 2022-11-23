package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.ISelectedItemDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;

import java.time.LocalDateTime;

public interface ISelectedItemService extends IService<ISelectedItem>{

    ISelectedItem create(ISelectedItemDTO selectedItemDTO);

    ISelectedItem update(long id, LocalDateTime dtUpdate, ISelectedItemDTO selectedItemDTO);

}
