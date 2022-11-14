package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuRowService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity.MenuRowService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.MenuRowDaoSingleton;

public class MenuRowServiceSingleton {

    private IMenuRowService menuRowService;

    private static volatile MenuRowServiceSingleton instance;

    public MenuRowServiceSingleton() {
        this.menuRowService = new MenuRowService(MenuRowDaoSingleton.getInstance());
    }

    public static IMenuRowService getInstance() {
        if (instance == null) {
            synchronized (MenuRowServiceSingleton.class) {
                if (instance == null) {
                    instance = new MenuRowServiceSingleton();
                }
            }
        }

        return instance.menuRowService;
    }
}

