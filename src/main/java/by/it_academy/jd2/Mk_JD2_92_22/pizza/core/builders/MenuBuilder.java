package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.builders;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.Menu;

import java.time.LocalDateTime;

public class MenuBuilder {

    private long id;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String name;
    private boolean enabled;

    private static final MenuBuilder INSTANCE = new MenuBuilder();

    private MenuBuilder() {
    }

    public static MenuBuilder create() {
        return new MenuBuilder();
    }

    public MenuBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public MenuBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public MenuBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MenuBuilder setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public IMenu build() {
        return new Menu(dtCreate, dtUpdate, name, enabled);
    }
}
