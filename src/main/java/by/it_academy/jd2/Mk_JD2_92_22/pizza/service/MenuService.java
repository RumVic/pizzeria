package by.it_academy.jd2.Mk_JD2_92_22.pizza.service;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.dao.api.IMenuDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;

import java.time.LocalDateTime;
import java.util.List;

public class MenuService implements IMenuService {

    private final IMenuDao menuDao;

    public MenuService(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public IMenu create(IMenu item) {

        item.setDtCreate(LocalDateTime.now());
        item.setDtUpdate(item.getDtCreate());

        return menuDao.create(item);
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
    public IMenu update(long id, LocalDateTime dtUpdate, IMenu item) {
        IMenu readed = menuDao.read(id);

        if(readed == null){
            throw new IllegalArgumentException("Меню не найдено");
        }

        if(!readed.getDtUpdate().isEqual(dtUpdate)){
            throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
        }

        readed.setDtUpdate(LocalDateTime.now());
        readed.setName(item.getName());
        readed.setEnabled(item.isEnabled());

        return menuDao.update(id, dtUpdate, readed);
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

    }
}
