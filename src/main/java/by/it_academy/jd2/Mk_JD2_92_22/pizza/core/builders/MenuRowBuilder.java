package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.MenuRow;

import java.time.LocalDateTime;

public class MenuRowBuilder {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private long info;

    private double price;

    private long menu;

    private IPizzaInfo pizzaInfo;

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

    public MenuRowBuilder setInfo(long info) {
        this.info = info;
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
    public MenuRowBuilder setPizzaInfo(IPizzaInfo pizzaInfo){
        this.pizzaInfo = pizzaInfo;
        return this;
    }

    public  IMenuRow build() {
        return new MenuRow(id,dtCreate,dtUpdate,info,price,menu,pizzaInfo);
    }

}
