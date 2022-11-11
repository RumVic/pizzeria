package by.it_academy.jd2.Mk_JD2_92_22.main;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.MenuServiceSingleton;
import java.util.List;


public class Main6 {
    public static void main(String[] args)  {


        /*List<IMenu> list = MenuDaoSingleton.getInstance().get();
        mapper.writeValueAsString(list);

        /*List<IMenu> list = MenuDaoSingleton.getInstance().get();
        System.out.println(list);
        try{
        .writeValueAsBytes(list);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }*/


/*       String name = MenuServiceSingleton.getInstance().read(1L).getName();
        System.out.println(name);*/


        List<IMenu> menuList = MenuServiceSingleton.getInstance().get();
        System.out.println(menuList);
    }
}

