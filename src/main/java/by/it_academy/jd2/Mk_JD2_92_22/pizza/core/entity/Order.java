package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IOrder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order implements IOrder {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private long state; //state cooking

    //private double price;

    //private long menu; // accordance menu_row to menu

    //private String infoClient;

    //private long count; // amount ordered positions


    //private String name; //pizza name from pizza info

    private List<ISelectedItem> listSelectedItem;

    public Order(){}

    public Order(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                 long state) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.state = state;
    }

    public Order(LocalDateTime dtCreate, LocalDateTime dtUpdate,
                 long state) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.state = state;
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
    public long getState() {return state;}

    @Override
    public List<ISelectedItem> getSelected() {
        return listSelectedItem;
    }

    public void setListSelectedItem(List<ISelectedItem> listSelectedItem) {
        this.listSelectedItem = listSelectedItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && state == order.state && dtCreate.equals(order.dtCreate) && dtUpdate.equals(order.dtUpdate) && listSelectedItem.equals(order.listSelectedItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, dtUpdate, state, listSelectedItem);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", state='" + state + '\'' +
                ", listSelectedItem=" + listSelectedItem +
                '}';
    }
}
