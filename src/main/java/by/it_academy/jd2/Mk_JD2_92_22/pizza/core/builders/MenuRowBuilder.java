package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.MenuRow;

import java.time.LocalDateTime;

public class MenuRowBuilder {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private long infoNumber;

    private double price;

    private long menu;


    private MenuRowBuilder() {};

    public static  MenuRowBuilder create(){
        return new MenuRowBuilder();
    }

    public MenuRowBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public MenuRowBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public MenuRowBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public MenuRowBuilder setInfoNumber(long infoNumber) {
        this.infoNumber = infoNumber;
        return this;
    }

    public MenuRowBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public MenuRowBuilder setMenu(long menu) {
        this.menu = menu;
        return this;
    }

    public  IMenuRow build() {
        return new MenuRow(id,dtCreate,dtUpdate, infoNumber,price,menu);
    }

}
