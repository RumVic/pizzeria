package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.ISelectedItemDTO;

public class SelectedItemDTO implements ISelectedItemDTO {

    private String infoClient;

    private long selectedPositions;

    public SelectedItemDTO() {
    }

    public SelectedItemDTO(String infoClient, long selectedPositions) {
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
    }

    @Override
    public String getInfoClient() {
        return infoClient;
    }

    @Override
    public long getSelectedPositions() {
        return selectedPositions;
    }
}

