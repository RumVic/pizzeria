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
        System.out.println("wtf1");
        IMenuDao menuDao = MenuDaoSingleton.getInstance();
        System.out.println("wtf2");
        IMenu menu = menuDao.read(1);
        menu.toString();
        System.out.println("wtf4");
        //check for the menu object gets list MenuRow and print it
        List<IMenuRow> list = menu.getItems();
        System.out.println("wtf5");




        /*
        List<IMenu> listMenu = menuDao.get();
        for (IMenu myMenu : listMenu
             ) {
            List<IMenuRow> menuRows = myMenu.getItems();
            for (IMenuRow iMenuRow:menuRows) {
                System.out.println(iMenuRow);
            }
        }

         */
    }
}
