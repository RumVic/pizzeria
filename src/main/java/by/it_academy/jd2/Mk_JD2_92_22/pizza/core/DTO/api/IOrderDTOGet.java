package by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.api;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IOrder;

public interface IOrderDTOGet extends IEssenceDTO<IOrder> {

     double getPrice();

     int getMenu(); // accordance menu_row to menu

     String getInfoClient();

     long getCount(); // amount ordered positions

     String getState(); //state cooking

     String getName(); //pizza name from pizza info

}
