package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton;

import by.it_academy.jd2.Mk_JD2_92_22.main.DataSourceCreator;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuRowDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity.MenuRowDao;

public class MenuRowDaoSingleton {

    private IMenuRowDao menuRowDao; //

    private volatile static MenuRowDaoSingleton instance;

    public MenuRowDaoSingleton() {
        try {
            this.menuRowDao = new MenuRowDao(DataSourceCreator.getInstance());
        } catch (Exception e) {
            throw new RuntimeException("There are problems with creating data layer ", e);
        }
    }
    public static IMenuRowDao getInstance() {
        if (instance == null) {
            synchronized (MenuRowDaoSingleton.class) {
                if (instance == null) {
                    instance = new MenuRowDaoSingleton();
                }
            }
        }
        return instance.menuRowDao;
    }

}
