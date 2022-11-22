package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders.MenuBuilder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;
import java.time.LocalDateTime;
import java.util.List;

public class MenuService implements IMenuService {

    private final IMenuDao menuDao;

    public MenuService(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }


    @Override
    public IMenu create(IMenuDTO menuDTO) {
        return menuDao.create(MenuBuilder.create()
                .setDtCreate(LocalDateTime.now())
                .setDtUpdate(LocalDateTime.now())
                .setName(menuDTO.getName())
                .setEnabled(menuDTO.isEnabled())
                .build());
    }

    @Override
    public IMenu read(long id) {
        return menuDao.read(id);
    }

    @Override
    public List<IMenu> get() {
        return menuDao.get();
    }


    @Override
    public IMenu update(long id, LocalDateTime dtUpdate, IMenuDTO menuDTO) {

        IMenu readed = menuDao.read(id);

        if (readed == null) {
            throw new IllegalArgumentException("Меню не найдено");
        }


        if (!readed.getDtUpdate().isEqual(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        IMenu menuUpdate = MenuBuilder.create()
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(LocalDateTime.now())
                .setName(menuDTO.getName())
                .setEnabled(menuDTO.isEnabled())
                .build();

        return menuDao.update(id, dtUpdate, menuUpdate);
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {
        menuDao.delete(id, dtUpdate);
    }
}
