package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.MenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuRowDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IPizzaInfoDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.singleton.PizzaInfoDaoSingleton;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MenuRowDao implements IMenuRowDao {

    private static final String INSERT_SQL = "INSERT INTO app.menu_row(\n" +
            "\tdt_create, dt_update,info,price,menu)\n" +
            "\tVALUES (?, ?, ?, ?, ?);";



    private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, info, price, menu\n" +
            "\tFROM app.menu_row\n" +
            "\tWHERE id = ?;";

    private static final String SELECT_SQL = "SELECT id, dt_create, dt_update, info, price, menu\n" +
            "\tFROM app.menu_row;";

    private static final String UPDATE_SQL = "UPDATE app.menu_row\n" +
            "\tSET dt_update = ?, info = ?, price = ?, menu = ?\n" +
            "\tWHERE id = ? and dt_update = ?;";

    private static final String DELETE_SQL = "DELETE FROM app.menu_row\n" +
            "\tWHERE id = ? and dt_update = ?;";

    private static final String SELECT_MENUROW_BY_MENUID = "SELECT id, dt_create, dt_update, info, price, menu\n" +
            "\tFROM app.menu_row\n" +
            "\twhere menu = ?\n" +
            "\t;";

    private final DataSource dataSource;

    private IPizzaInfoDao pizzaInfoDao;

    public MenuRowDao(DataSource dataSource){
        this.dataSource = dataSource;
        this.pizzaInfoDao = PizzaInfoDaoSingleton.getInstance();
    };


    @Override
    public IMenuRow create(IMenuRow item) {

       try (Connection conn = dataSource.getConnection();
             PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stm.setObject(1, item.getDtCreate());
            stm.setObject(2, item.getDtUpdate());
            stm.setLong(3, item.getInfoNumber());
            stm.setDouble(4, item.getPrice());
            stm.setLong(5,item.getMenu());

            int updated = stm.executeUpdate();

            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                return read(generatedKeys.getLong(1));
            }
            return read(generatedKeys.getLong(1));


            //stm.getGeneratedKeys().getLong(1)
            //return id from created line in first colum
        } catch (SQLException e) {
            throw new RuntimeException("При сохранении данных произошла ошибка", e);
        }
    }

    @Override
    public IMenuRow read(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setObject(1, id);   //probably it is being put in WHERE construction

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("При чтении данных произошла ошибка", e);
        }
        return null;
    }

    @Override
    public List get() {
        List<IMenuRow> data = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    data.add(mapper(resultSet));
                }
            }
        } catch (SQLException s) {
            System.out.println("It is just a line for a while ");
        }
        return data;
    }

    @Override
    public IMenuRow update(long id, LocalDateTime dtUpdate, IMenuRow item) {
        try(Connection connection  = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL,Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setObject(1, item.getDtUpdate());
            preparedStatement.setLong(2, item.getInfoNumber());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setLong(4,item.getMenu());
            preparedStatement.setLong(5, id);
            preparedStatement.setObject(6,dtUpdate);

            int countUpdatedRows = preparedStatement.executeUpdate();

            if (countUpdatedRows != 1) {
                if (countUpdatedRows == 0) {
                    throw new IllegalArgumentException("I can't update some entry");//Не смогли обновить какую либо запись
                } else {
                    throw new IllegalArgumentException("There are several entries updated");//Обновили более одной записи
                }
            }
            return read(id);

        }catch (SQLException e) {
            throw new RuntimeException("При сохранении данных произошла ошибка", e);
        }
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL,Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1,id);
            preparedStatement.setObject(2,dtUpdate);

            int countUpdatedRows = preparedStatement.executeUpdate();

            if (countUpdatedRows != 1) {
                if (countUpdatedRows == 0) {
                    throw new IllegalArgumentException("Не смогли удалить какую либо запись");
                } else {
                    throw new IllegalArgumentException("Удалили более одной записи");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("При сохранении данных произошла ошибка", e);
        }
    }

    public List<IMenuRow> readByMenuId (long idMenu) {
        List<IMenuRow> menuRowList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENUROW_BY_MENUID, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, idMenu);   //probably it is being put in WHERE construction

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    menuRowList.add(mapper(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("При чтении данных произошла ошибка", e);
        }
        return menuRowList;
    }


    public IMenuRow mapper (ResultSet resultSet) throws SQLException {
        return new MenuRow(
                resultSet.getLong(1),
                resultSet.getObject(2,LocalDateTime.class),
                resultSet.getObject(3,LocalDateTime.class),
                resultSet.getLong(4),
                resultSet.getDouble(5),
                resultSet.getLong(6),
                pizzaInfoDao.read(resultSet.getLong(4)));
    }
}
