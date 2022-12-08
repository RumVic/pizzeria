package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IOrderService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity.OrderService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.OrderDaoSingleton;

public class OrderServiceSingleton {

    public IOrderService orderService;

    public static volatile OrderServiceSingleton instance;

    public OrderServiceSingleton() {
        this.orderService = new OrderService(OrderDaoSingleton.getInstance());
    }

    public static IOrderService getInstance(){
        if(instance ==null){
            synchronized (OrderServiceSingleton.class){
                if(instance == null){
                    instance = new OrderServiceSingleton();
                }
            }
        }return instance.orderService;
    }

}
