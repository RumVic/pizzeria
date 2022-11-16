package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import java.time.LocalDateTime;
import java.util.List;

public class Menu implements IMenu {
    private long id;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String name;
    private boolean enabled;
    private List<IMenuRow> items;

    public Menu(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enabled, List<IMenuRow> items) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enabled = enabled;
        this.items = items;
    }

    public Menu(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enabled) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enabled = enabled;
    }

    public Menu(LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enabled) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enabled = enabled;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public List<IMenuRow> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", items=" + items +
                '}';
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
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
