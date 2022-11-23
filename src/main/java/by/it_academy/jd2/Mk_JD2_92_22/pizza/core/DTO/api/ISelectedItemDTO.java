package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;

public interface ISelectedItemDTO extends IEssenceDTO<ISelectedItem> {

    String getInfoClient();

    long getSelectedPositions();


}
