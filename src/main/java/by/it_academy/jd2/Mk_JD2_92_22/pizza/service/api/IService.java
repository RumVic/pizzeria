package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IEssenceDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IService<TYPE>  {

    TYPE read(long id);

    List<TYPE> get();


    void delete(long id, LocalDateTime dtUpdate);
}
