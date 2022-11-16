package by.it_academy.jd2.Mk_JD2_92_22.pizza.service;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.MenuDaoSingleton;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MenuDaoTest {
    @Test
    public void readTest(){

        IMenuDao menuDao = MenuDaoSingleton.getInstance();
        //IMenu menu = menuDao.read(1);
        //check for the menu object gets list MenuRow and print it
      /*  List<IMenuRow> list = menu.getItems();
        for (IMenuRow iMenuRow :list)
              {
                  System.out.println(iMenuRow);
        }*/

        //
        List<IMenu> listMenu = menuDao.get();
        for (IMenu myMenu : listMenu
             ) {
            List<IMenuRow> menuRows = myMenu.getItems();
            for (IMenuRow iMenuRow:menuRows) {
                System.out.println(iMenuRow);
            }
        }



    }
}
