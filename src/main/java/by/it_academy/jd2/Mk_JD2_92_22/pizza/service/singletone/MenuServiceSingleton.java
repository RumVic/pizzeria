package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.MenuDaoSingleton;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity.MenuService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;

public class MenuServiceSingleton {
    private static volatile MenuServiceSingleton instance;

    private final IMenuService menuService;

    private MenuServiceSingleton() {
        menuService = new MenuService(MenuDaoSingleton.getInstance());
    }

    public static IMenuService getInstance() {
        if (instance == null) {
            synchronized (MenuServiceSingleton.class) {
                if (instance == null) {
                    instance = new MenuServiceSingleton();
                }
            }
        }

        return instance.menuService;
    }
}