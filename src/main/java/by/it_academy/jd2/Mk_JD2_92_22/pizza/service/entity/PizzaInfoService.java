package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IPizzaInfoDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders.PizzaInfoBuilder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IPizzaInfoService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IPizzaInfoDao;

import java.time.LocalDateTime;
import java.util.List;

public class PizzaInfoService implements IPizzaInfoService {

    private final IPizzaInfoDao pizzaInfoDao;

    public PizzaInfoService(IPizzaInfoDao pizzaInfoDao) {
        this.pizzaInfoDao = pizzaInfoDao;}

    @Override
    public IPizzaInfo create(IPizzaInfoDTO pizzaInfoDTO) {
               return pizzaInfoDao.create(PizzaInfoBuilder.create()
                .setDtCreate(LocalDateTime.now())
                .setDtUpdate(LocalDateTime.now())
                .setName(pizzaInfoDTO.getName())
                .setDescription(pizzaInfoDTO.getDescription())
                .setSize(pizzaInfoDTO.getSize())
                .build());
    }

    @Override
    public IPizzaInfo update(long id, LocalDateTime dtUpdate, IPizzaInfoDTO pizzaInfoDTO) {


        IPizzaInfo readed = pizzaInfoDao.read(id);

        if (readed == null) {
            throw new IllegalArgumentException("Меню не найдено");
        }


        if (!readed.getDtUpdate().isEqual(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        IPizzaInfo pizzaInfoUpdate = PizzaInfoBuilder.create()
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(LocalDateTime.now())
                .setName(pizzaInfoDTO.getName())
                .setDescription(pizzaInfoDTO.getDescription())
                .setSize(pizzaInfoDTO.getSize())
                .build();

        return pizzaInfoDao.update(id, dtUpdate, pizzaInfoUpdate);

    }

    @Override
    public IPizzaInfo read(long id) {
        return pizzaInfoDao.read(id);
    }

    @Override
    public List<IPizzaInfo> get() {
        return pizzaInfoDao.get();
    }


    @Override
    public void delete(long id, LocalDateTime dtUpdate) {
        pizzaInfoDao.delete(id, dtUpdate);
    }
}

