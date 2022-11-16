package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api;

import java.time.LocalDateTime;

/**
 * Информация о пицце
 */
public interface IPizzaInfo {

    long getId();

    LocalDateTime getDtCreate();

    LocalDateTime getDtUpdate();

    void setDtCreate(LocalDateTime dateTime);

    void setDtUpdate(LocalDateTime dateTime);

    void setName(String name);

    void setDescription(String description);

    void setSize(int size);


    /**
     * Название пиццы
     * @return
     */
    String getName();

    /**
     * Описание/состав пиццы
     * @return
     */
    String getDescription();

    /**
     * Итоговый размер пиццы которую приготовят
     * (все пиццы одного размера
     * @return
     */
    int getSize();




}
