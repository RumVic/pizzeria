package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api;


import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.MenuDTO;

public interface IMenuDTO extends IEssenceDTO<MenuDTO> {
    String getName();

    boolean isEnabled();
}
