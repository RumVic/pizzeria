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

    private IMenuRow menuRowPosition;

    public SelectedItem(long id, LocalDateTime dtCreate, LocalDateTime stUpdate, String infoClient, long selectedPositions) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = stUpdate;
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
    }

    public SelectedItem(LocalDateTime dtCreate, LocalDateTime stUpdate, String infoClient, long selectedPositions) {
        this.dtCreate = dtCreate;
        this.dtUpdate = stUpdate;
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
    }

    public SelectedItem(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String infoClient, long selectedPositions, IMenuRow menuRowPosition) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
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
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
