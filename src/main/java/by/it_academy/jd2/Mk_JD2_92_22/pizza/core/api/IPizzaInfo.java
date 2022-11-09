package by.it_academy.jd2.Mk_JD2_92_22.pizza.api;

/**
 * Информация о пицце
 */
public interface IPizzaInfo {

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
