package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.SelectedItem;

import java.time.LocalDateTime;

public class SelectedItemBuilder {

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private String infoClient;

    private long selectedPositions;

    private long count;

    private long order;


    private SelectedItemBuilder() {
    }

    public static SelectedItemBuilder create(){
        return new SelectedItemBuilder();
    }

    public SelectedItemBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public SelectedItemBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public SelectedItemBuilder setInfoClient(String infoClient) {
        this.infoClient = infoClient;
        return this;
    }

    public SelectedItemBuilder setSelectedPositions(long selectedPositions) {
        this.selectedPositions = selectedPositions;
        return this;
    }

    public SelectedItemBuilder setCount(long count) {
        this.count = count;
        return this;
    }

    public SelectedItemBuilder setOrder(long order) {
        this.order = order;
        return this;
    }

    public ISelectedItem build(){
        return new SelectedItem(dtCreate,dtUpdate,infoClient,selectedPositions,count,order);
    }

}
