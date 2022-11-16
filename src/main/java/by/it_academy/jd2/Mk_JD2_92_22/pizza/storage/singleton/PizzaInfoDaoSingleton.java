package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton;

import by.it_academy.jd2.Mk_JD2_92_22.main.DataSourceCreator;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IPizzaInfoDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity.PizzaInfoDao;

public class PizzaInfoDaoSingleton {


    private IPizzaInfoDao pizzaInfoDao;

    private volatile static PizzaInfoDaoSingleton instance;

    public PizzaInfoDaoSingleton() {
        try {
            this.pizzaInfoDao = new PizzaInfoDao(DataSourceCreator.getInstance());
        } catch (Exception e) {
            throw new RuntimeException("There are problems with creating data layer ", e);
        }
    }
    public static IPizzaInfoDao getInstance() {
        if (instance == null) {
            synchronized (PizzaInfoDaoSingleton.class) {
                if (instance == null) {
                    instance = new PizzaInfoDaoSingleton();
                }
            }
        }
        return instance.pizzaInfoDao;
    }

}

