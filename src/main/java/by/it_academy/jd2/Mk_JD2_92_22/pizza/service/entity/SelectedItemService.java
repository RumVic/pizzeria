package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.ISelectedItemDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders.SelectedItemBuilder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.ISelectedItemService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.ISelectedItemDao;

import java.time.LocalDateTime;
import java.util.List;

public class SelectedItemService implements ISelectedItemService {

    private  ISelectedItemDao selectedItemDao;

    public SelectedItemService(ISelectedItemDao selectedItemDao) {
        this.selectedItemDao = selectedItemDao;
    }





    @Override
    public ISelectedItem create(ISelectedItemDTO selectedItemDTO) {
          return selectedItemDao.create(SelectedItemBuilder.create()
                .setDtCreate(LocalDateTime.now())
                .setDtUpdate(LocalDateTime.now())
                .setInfoClient(selectedItemDTO.getInfoClient())
                .setSelectedPositions(selectedItemDTO.getSelectedPositions())
                .build());
    }

    @Override
    public ISelectedItem update(long id, LocalDateTime dtUpdate, ISelectedItemDTO selectedItemDTO) {

        ISelectedItem readed = selectedItemDao.read(id);

        if (readed == null) {
            throw new IllegalArgumentException("This position wasn't find");
        }


        if (!readed.getDtUpdate().isEqual(dtUpdate)) {
            throw new IllegalArgumentException("Unfortunately this line was fixed somebody else");
        }

        ISelectedItem menuUpdate = SelectedItemBuilder.create()
                .setDtCreate(readed.getDtCreate())
                .setDtUpdate(LocalDateTime.now())
                .setInfoClient(selectedItemDTO.getInfoClient())
                .setSelectedPositions(selectedItemDTO.getSelectedPositions())
                .build();

        return selectedItemDao.update(id, dtUpdate, menuUpdate);

    }

    @Override
    public ISelectedItem read(long id) {
        return selectedItemDao.read(id);
    }

    @Override
    public List<ISelectedItem> get() {
        return selectedItemDao.get();
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

    }
}
