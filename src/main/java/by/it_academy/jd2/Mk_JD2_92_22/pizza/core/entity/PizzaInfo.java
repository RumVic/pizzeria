package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;

import java.time.LocalDateTime;
import java.util.Objects;

public class PizzaInfo implements IPizzaInfo {

    private long id;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private String name;

    private String description;

    private int size;

    public PizzaInfo(String name){
        this.name = name;
    }

    public PizzaInfo(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, String description, int size) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.description = description;
        this.size = size;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return this.dtCreate ;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return this.dtUpdate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Override
    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaInfo pizzaInfo = (PizzaInfo) o;
        return id == pizzaInfo.id && size == pizzaInfo.size && dtCreate.equals(pizzaInfo.dtCreate) && dtUpdate.equals(pizzaInfo.dtUpdate) && name.equals(pizzaInfo.name) && description.equals(pizzaInfo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, dtUpdate, name, description, size);
    }

    @Override
    public String toString() {
        return "PizzaInfo{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                '}';
    }
}
