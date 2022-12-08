package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IOrderDTOCreate;

import java.util.Objects;

public class OrderDTOCreate implements IOrderDTOCreate {

    private String state;

    public OrderDTOCreate() {
    }

    public OrderDTOCreate(String state) {
        this.state = state;
    }

    @Override
    public String getState() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTOCreate that = (OrderDTOCreate) o;
        return state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "OrderDTOCreate{" +
                "state='" + state + '\'' +
                '}';
    }
}
