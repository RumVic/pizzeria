package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton;

import by.it_academy.jd2.Mk_JD2_92_22.main.DataSourceCreator;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IOrderDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity.OrderDao;


public class OrderDaoSingleton {

    public static volatile OrderDaoSingleton instance;

    private IOrderDao orderDao;

    public OrderDaoSingleton() {
        try {
            this.orderDao = new OrderDao(DataSourceCreator.getInstance());
        } catch (Exception e) {
            throw new RuntimeException("There are problems with creating data layer ", e);

        }
    }

        public static IOrderDao getInstance () {
            if (instance == null) {
                synchronized (OrderDaoSingleton.class) {
                    if (instance == null) {
                        instance = new OrderDaoSingleton();
                    }
                }
            }
            return instance.orderDao;
        }
    }

