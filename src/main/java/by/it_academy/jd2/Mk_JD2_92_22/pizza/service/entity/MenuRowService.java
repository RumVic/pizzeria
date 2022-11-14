package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuRowService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuRowDao;
import java.time.LocalDateTime;
import java.util.List;

public class MenuRowService implements IMenuRowService {

    private IMenuRowDao menuRowDao;

    public MenuRowService(IMenuRowDao menuRowDao) {
        this.menuRowDao = menuRowDao;
    }

    @Override
    public IMenuRow create(IMenuDTO menuDTO) {
        return null;
    }

    @Override
    public IMenuRow read(long id) {
        return menuRowDao.read(id);
    }

    @Override
    public List<IMenuRow> get() {
        return menuRowDao.get();
    }

    @Override
    public IMenuRow update(long id, LocalDateTime dtUpdate, IMenuDTO menuDTO) {
        return null;
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

    }
}
