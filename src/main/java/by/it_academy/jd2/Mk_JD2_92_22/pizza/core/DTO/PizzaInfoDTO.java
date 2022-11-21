package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IPizzaInfoDTO;

import java.util.Objects;

public class PizzaInfoDTO implements IPizzaInfoDTO {

    private String name;

    private String description;

    private int size;

    public PizzaInfoDTO() {
    }

    public PizzaInfoDTO(String name, String description, int size) {
        this.name = name;
        this.description = description;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaInfoDTO that = (PizzaInfoDTO) o;
        return size == that.size && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, size);
    }

    @Override
    public String toString() {
        return "PizzaInfoDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                '}';
    }
}
