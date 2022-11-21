package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.PizzaInfo;

import java.time.LocalDateTime;

public class PizzaInfoBuilder {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private String name;

    private String description;

    private int size;

    private PizzaInfoBuilder() {
    }

    public static PizzaInfoBuilder create() {
        return new PizzaInfoBuilder();
    }

    public PizzaInfoBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public PizzaInfoBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public PizzaInfoBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public PizzaInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PizzaInfoBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PizzaInfoBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public IPizzaInfo build() {
        return new PizzaInfo(id,dtCreate,dtUpdate,name,description,size);
    }
}
