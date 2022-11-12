package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IService<TYPE> {
    TYPE create(IMenuDTO menuDTO);
    TYPE read(long id);
    List<TYPE> get();
    TYPE update(long id, LocalDateTime dtUpdate, TYPE item);
    void delete(long id, LocalDateTime dtUpdate);
}
