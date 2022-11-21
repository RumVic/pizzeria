package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity;


import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import java.time.LocalDateTime;
import java.util.Objects;

public class MenuRow implements IMenuRow {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private long infoNumber;

    private double price;

    private long menu;

    private IPizzaInfo pizzaInfo;

    public MenuRow(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, long info, double price, long menu,IPizzaInfo pizzaInfo) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.infoNumber = info;
        this.price = price;
        this.menu = menu;;
        this.pizzaInfo = pizzaInfo;
    }

    @Override
    public long getId() {return id;}
    @Override
    public long getInfoNumber() {
        return infoNumber;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Override
    public long getMenu() {
        return menu;
    }

    @Override
    public void setId() {}

    @Override
    public void setDtCreate() {}

    @Override
    public void setDtUpdate() {}

    @Override
    public void setInfoNumber() {}

    @Override
    public void setPrice() {}

    @Override
    public void setMenu() {}

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public IPizzaInfo getInfo() {
        return pizzaInfo ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRow menuRow = (MenuRow) o;
        return id == menuRow.id && infoNumber == menuRow.infoNumber && Double.compare(menuRow.price, price) == 0 && menu == menuRow.menu && dtCreate.equals(menuRow.dtCreate) && dtUpdate.equals(menuRow.dtUpdate) && pizzaInfo.equals(menuRow.pizzaInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, dtUpdate, infoNumber, price, menu, pizzaInfo);
    }

    @Override
    public String toString() {
        return "MenuRow{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", infoNumber=" + infoNumber +
                ", price=" + price +
                ", menu=" + menu +
                '}';
    }
}