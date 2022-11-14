package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api;

import java.time.LocalDateTime;

/**
 * Строчка меню
 */
public interface IMenuRow {

    long getId();

    LocalDateTime getDtUpdate();

    LocalDateTime getDtCreate();

    long getMenu();

    long getInfoNumber();

    void setId();

    void setDtCreate();

    void setDtUpdate();

    void setInfoNumber();

    void setPrice();

    void setMenu();



    /**
     * Информация о пицце
     *
     * @return
     */
    IPizzaInfo getInfo();

    /**
     * Стоимость пиццы
     *
     * @return
     */
    double getPrice();
}
