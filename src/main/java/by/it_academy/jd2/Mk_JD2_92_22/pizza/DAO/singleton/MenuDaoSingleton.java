
package by.it_academy.jd2.Mk_JD2_92_22.pizza.DAO.singleton;
import by.it_academy.jd2.Mk_JD2_92_22.main.DataSourceCreator;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.DAO.entity.MenuDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.DAO.api.IMenuDao;

public class MenuDaoSingleton {
    private IMenuDao menuDao;
    private volatile static MenuDaoSingleton instance;

    public MenuDaoSingleton() {
        try{
            this.menuDao = new MenuDao(DataSourceCreator.getInstance());
        } catch (Exception e){
            throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
        }
    }

    public static IMenuDao getInstance() {
        if(instance == null){
            synchronized (MenuDaoSingleton.class){
                if(instance == null){
                    instance = new MenuDaoSingleton();
                }
            }
        }
        return instance.menuDao;
    }
}
