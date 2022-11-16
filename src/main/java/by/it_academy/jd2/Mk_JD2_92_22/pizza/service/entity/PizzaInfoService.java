package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IPizzaInfoService;

import java.time.LocalDateTime;
import java.util.List;

public class PizzaInfoService implements IPizzaInfoService {
    @Override
    public IPizzaInfo create(IMenuDTO menuDTO) {
        return null;
    }

    @Override
    public IPizzaInfo read(long id) {
        return null;
    }

    @Override
    public List<IPizzaInfo> get() {
        return null;
    }

    @Override
    public IPizzaInfo update(long id, LocalDateTime dtUpdate, IMenuDTO menuDTO) {
        return null;
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

    }
}
