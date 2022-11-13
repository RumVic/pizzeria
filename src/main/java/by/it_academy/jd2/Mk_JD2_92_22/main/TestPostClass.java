package by.it_academy.jd2.Mk_JD2_92_22.main;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.DTO.MenuDTO;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.api.IMenuService;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.service.singletone.MenuServiceSingleton;

public class TestPostClass {
    private IMenuService menuService;

    public TestPostClass(){
        this.menuService = MenuServiceSingleton.getInstance();
    }

    public void mainSix() {
        MenuDTO menuDTO = new MenuDTO("extension", true);
        System.out.println(menuService.create(menuDTO));
    }
}
