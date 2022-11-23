package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuRowDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders.MenuRowBuilder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuRowService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuRowDao;
import java.time.LocalDateTime;
import java.util.List;

public class MenuRowService implements IMenuRowService {

    private final IMenuRowDao menuRowDao;

    public MenuRowService(IMenuRowDao menuRowDao) {
        this.menuRowDao = menuRowDao;
    }



    @Override
    public IMenuRow create(IMenuRowDTO menuRowDTO) {
        return menuRowDao.create(MenuRowBuilder
                .create()
                .setDtCreate(LocalDateTime.now())
                .setDtUpdate(LocalDateTime.now())
                .setInfoNumber(menuRowDTO.getInfoNumber())
                .setPrice(menuRowDTO.getPrice())
                .setMenu(menuRowDTO.getMenu())
                .build());
    }

    @Override
    public IMenuRow update(long id, LocalDateTime dtUpdate, IMenuRowDTO menuRowDTO) {

         IMenuRow readed = menuRowDao.read(id);

        if (readed == null) {
            throw new IllegalArgumentException("Menu wasn't find");
        }


        if (!readed.getDtUpdate().isEqual(dtUpdate)) {
            throw new IllegalArgumentException("Unfortunately this line was edit somebody else");
        }

        IMenuRow menuRowUpdate = MenuRowBuilder.create()
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(LocalDateTime.now())
                .setInfoNumber(menuRowDTO.getInfoNumber())
                .setPrice(menuRowDTO.getPrice())
                .setMenu(menuRowDTO.getMenu())
                .build();

        return menuRowDao.update(id, dtUpdate, menuRowUpdate);

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
    public void delete(long id, LocalDateTime dtUpdate) {
        menuRowDao.delete(id, dtUpdate);

    }
}
