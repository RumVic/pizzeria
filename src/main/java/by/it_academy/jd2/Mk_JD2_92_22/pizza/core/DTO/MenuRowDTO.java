package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuRowDTO;

import java.util.Objects;

public class MenuRowDTO implements IMenuRowDTO {

    private long infoNumber;
    private double price;
    private long menu;

    public MenuRowDTO() {
    }

    public MenuRowDTO(long info, double price, long menu) {
        this.infoNumber = info;
        this.price = price;
        this.menu = menu;
    }

    @Override
    public long getInfoNumber() {
        return infoNumber;
    }

    public double getPrice() {
        return price;
    }

    public long getMenu() {
        return menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRowDTO that = (MenuRowDTO) o;
        return infoNumber == that.infoNumber && Double.compare(that.price, price) == 0 && menu == that.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoNumber, price, menu);
    }

    @Override
    public String toString() {
        return "MenuRowDTO{" +
                "info=" + infoNumber +
                ", price=" + price +
                ", menu=" + menu +
                '}';
    }
}
