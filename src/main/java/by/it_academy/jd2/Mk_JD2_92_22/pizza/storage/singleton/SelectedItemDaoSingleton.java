package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton;

import by.it_academy.jd2.Mk_JD2_92_22.main.DataSourceCreator;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.ISelectedItemDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity.SelectedItemDao;

public class SelectedItemDaoSingleton {

    private ISelectedItemDao selectedItemDao;

    private volatile static SelectedItemDaoSingleton instance;

    public SelectedItemDaoSingleton() {
        try {
            this.selectedItemDao = new SelectedItemDao(DataSourceCreator.getInstance());
        } catch (Exception e) {
            throw new RuntimeException("There are problems with creating data layer ", e);
        }
    }
    public static ISelectedItemDao getInstance() {
        if (instance == null) {
            synchronized (SelectedItemDaoSingleton.class) {
                if (instance == null) {
                    instance = new SelectedItemDaoSingleton();
                }
            }
        }
        return instance.selectedItemDao;
    }


}
