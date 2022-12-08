package by.it_academy.jd2.Mk_JD2_92_22.pizza.service.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IOrder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IOrderService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.OrderServiceSingleton;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IOrderDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.OrderDaoSingleton;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService implements IOrderService {


    private final IOrderDao orderDao;

    public OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public IOrder read(long id) {
      return orderDao.read(id);
    }

    @Override
    public List<IOrder> get() {
        return null;
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

    }
}
