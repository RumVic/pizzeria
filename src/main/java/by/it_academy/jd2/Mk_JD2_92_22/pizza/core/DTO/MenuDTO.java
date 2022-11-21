package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.IMenuDTO;

import java.util.Objects;

public class MenuDTO implements IMenuDTO {



    private String name;
    private boolean enabled;


    public MenuDTO() {
    }

    public MenuDTO(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDTO menuDTO = (MenuDTO) o;
        return enabled == menuDTO.enabled && name.equals(menuDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, enabled);
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
