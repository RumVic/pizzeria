package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api.ISelectedItemDTO;

public class SelectedItemDTO implements ISelectedItemDTO {

    private String infoClient;

    private long selectedPositions;

    private long count;

    private long order;

    public SelectedItemDTO() {
    }

    public SelectedItemDTO(String infoClient, long selectedPositions, long count, long order) {
        this.infoClient = infoClient;
        this.selectedPositions = selectedPositions;
        this.count = count;
        this.order = order;
    }

    @Override
    public String getInfoClient() {
        return infoClient;
    }

    @Override
    public long getSelectedPositions() {
        return selectedPositions;
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public long getOrder() {
        return order;
    }
}

