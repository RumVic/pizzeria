package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IPizzaInfoDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;

import java.time.LocalDateTime;

public interface IPizzaInfoService extends IService<IPizzaInfo> {

    IPizzaInfo create(IPizzaInfoDTO pizzaInfoDTO);

    IPizzaInfo update(long id, LocalDateTime dtUpdate, IPizzaInfoDTO pizzaInfoDTO);
}
