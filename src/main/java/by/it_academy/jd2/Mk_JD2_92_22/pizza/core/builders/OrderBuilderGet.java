package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IOrder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderBuilderGet {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private long state; //state cooking

    private List<ISelectedItem> list;

    private OrderBuilderGet() {
    }

    public static OrderBuilderGet create(){
        return new OrderBuilderGet();
    }

    public OrderBuilderGet setList(List<ISelectedItem> list) {
        this.list = list;
        return this;
    }

    public OrderBuilderGet setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public OrderBuilderGet setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public OrderBuilderGet setState(long state) {
        this.state = state;
        return this;
    }

    public  IOrder build(){
        return new Order(dtCreate,dtUpdate,state);
    }

}
