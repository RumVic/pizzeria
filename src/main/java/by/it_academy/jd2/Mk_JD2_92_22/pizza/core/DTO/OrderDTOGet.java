package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IOrderDTOGet;

import java.util.Objects;

public class OrderDTOGet implements IOrderDTOGet {

    private double price;

    private int menu; // accordance menu_row to menu

    private String infoClient;

    private long count; // amount ordered positions

    private String state; //state cooking

    private String name; //pizza name from pizza info


    public OrderDTOGet() { //for mapper
    }

    public OrderDTOGet(double price, int menu, String infoClient,
                       long count, String state, String name) {
        this.price = price;
        this.menu = menu;
        this.infoClient = infoClient;
        this.count = count;
        this.state = state;
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getMenu() {
        return menu;
    }

    @Override
    public String getInfoClient() {
        return infoClient;
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTOGet that = (OrderDTOGet) o;
        return Double.compare(that.price, price) == 0 && menu == that.menu && count == that.count && infoClient.equals(that.infoClient) && state.equals(that.state) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, menu, infoClient, count, state, name);
    }

    @Override
    public String toString() {
        return "OrderDTOGet{" +
                "price=" + price +
                ", menu=" + menu +
                ", infoClient='" + infoClient + '\'' +
                ", count=" + count +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
