package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IPizzaInfoService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity.PizzaInfoService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.PizzaInfoDaoSingleton;

public class PizzaInfoServiceSingleton {

    private static volatile PizzaInfoServiceSingleton instance;

    private final IPizzaInfoService pizzaInfoService;

    private PizzaInfoServiceSingleton(){
        pizzaInfoService = new PizzaInfoService(PizzaInfoDaoSingleton.getInstance());
    }

    public static IPizzaInfoService getInstance() {
        if (instance == null) {
            synchronized (PizzaInfoServiceSingleton.class) {
                if (instance == null) {
                    instance = new PizzaInfoServiceSingleton();
                }
            }
        }
        return instance.pizzaInfoService;
    }

}
