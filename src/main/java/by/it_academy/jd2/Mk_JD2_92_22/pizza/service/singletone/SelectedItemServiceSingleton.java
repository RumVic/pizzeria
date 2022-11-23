package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.ISelectedItemService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity.SelectedItemService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.SelectedItemDaoSingleton;

public class SelectedItemServiceSingleton {

    private ISelectedItemService selectedItemService;

    private static volatile SelectedItemServiceSingleton instance;

    public SelectedItemServiceSingleton() {
            this.selectedItemService = new SelectedItemService(SelectedItemDaoSingleton.getInstance());

    }

    public static ISelectedItemService getInstance(){
        if (instance == null) {
            synchronized (SelectedItemServiceSingleton.class) {
                if (instance == null) {
                    instance = new SelectedItemServiceSingleton();
                }
            }
        }
            return instance.selectedItemService;
        }
    }
