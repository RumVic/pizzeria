package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api;

import java.time.LocalDateTime;

/**
 * Выбор покупателя
 */
public interface ISelectedItem {


    long getId();

    LocalDateTime getDtCreate();

    LocalDateTime getDtUpdate();

    String getInfoClient();

    long getSelectedPositions();


    /**
     * Выбранное из меню
     * @return
     */
    IMenuRow getRow();

    /**
     * Количество выбранного
     * @return
     */
    int getCount();
}
