package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;

import java.time.LocalDateTime;

public class SelectedItem implements ISelectedItem {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private String infoClient;

    private long selectedPositions;

    private long count;

    private long order;

    private IMenuRow menuRowPosition;

    public SelectedItem(long id,String infoClient,long count){
        this.id = id;
        this.infoClient = infoClient;
        this.count = count;
    }

    public SelectedItem(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                        String infoClient, long selectedPositions,
                        long count, long order) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
        this.count = count;
        this.order = order;
    }

    public SelectedItem(LocalDateTime dtCreate,
                        LocalDateTime dtUpdate, String infoClient,
                        long selectedPositions, long count, long order) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
        this.count = count;
        this.order = order;
    }

    public SelectedItem(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                        String infoClient, long selectedPositions, long count, long order,
                        IMenuRow menuRowPosition) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
        this.count = count;
        this.order = order;
        this.menuRowPosition = menuRowPosition;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    @Override
    public String getInfoClient() {
        return infoClient;
    }

    @Override
    public long getSelectedPositions() {
        return selectedPositions;
    }

    @Override
    public IMenuRow getRow() {
        return menuRowPosition;
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public long getOrder() {
        return order;
    }

    public void setMenuRowPosition(IMenuRow menuRowPosition) {
        this.menuRowPosition = menuRowPosition;
    }
}
