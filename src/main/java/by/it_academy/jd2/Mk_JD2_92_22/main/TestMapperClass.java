package by.it_academy.jd2.Mk_JD2_92_22.main;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.MenuDaoSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class TestMapperClass {
    public  ObjectMapper mapper;

    public TestMapperClass(){
        this.mapper = new ObjectMapper();
    }

    public void mainSix() {
        List<IMenu> list = MenuDaoSingleton.getInstance().get();
        System.out.println(list);
        try{
            mapper.writeValueAsBytes(list);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
