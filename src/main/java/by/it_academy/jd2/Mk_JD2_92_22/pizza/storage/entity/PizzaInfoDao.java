package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IPizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.PizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IPizzaInfoDao;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PizzaInfoDao implements IPizzaInfoDao {

    private static final String INSERT_SQL = "INSERT INTO app.pizza_info(\n" +
            "\tdt_create, dt_update, name, description,size)\n" +
            "\tVALUES (?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, name, description, size\n" +
            "\tFROM app.pizza_info\n" +
            "\tWHERE id = ?;";

    private static final String SELECT_SQL = "SELECT id, dt_create, dt_update, name, description, size\n" +
            "\tFROM app.pizza_info;";

    private static final String UPDATE_SQL = "UPDATE app.pizza_info\n" +
            "\tSET dt_update = ?, name = ?, description = ?, size = ?\n" +
            "\tWHERE id = ? and dt_update = ?;";

    private static final String DELETE_SQL = "DELETE FROM app.pizza_info\n" +
            "\tWHERE id = ? and dt_update = ?;";

    private static final String SELECT_PIZZAINFO_BY_ID = "SELECT id, dt_create, dt_update, name, description, size\n" +
            "\tFROM app.pizza_info\n" +
            "\twhere id = ?\n" +
            "\t;";


    private final DataSource dataSource;

    private IPizzaInfoDao pizzaInfoDao;

    public PizzaInfoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public IPizzaInfo create(IPizzaInfo item) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setObject(1, item.getDtCreate());
            preparedStatement.setObject(2, item.getDtUpdate());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setInt(5,item.getSize());

            int updated = preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {                         // what the fuck here are written ??(fix if)
                return read(generatedKeys.getLong(1));
            }
            return read(generatedKeys.getLong(1));


            //preparedStatement.getGeneratedKeys().getLong(1)
            //return id from created line in resultSet colum
        } catch (SQLException e) {
            throw new RuntimeException("The exception occurred while data was saving", e);
        }
    }

    @Override
    public IPizzaInfo read(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)
        ) {
            preparedStatement.setObject(1, id);//probably it is being put in WHERE construction

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return mapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("The exception occurred while data was riding", e);
        }

        return null;
    }

    @Override
    public List<IPizzaInfo> get() {
        List<IPizzaInfo> data = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SELECT_SQL)
        ) {
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                while (resultSet.next()) {
                    data.add(mapper(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("The exception occurred while data was saving", e);
        }

        return data;
    }

    @Override
    public IPizzaInfo update(long id, LocalDateTime dtUpdate, IPizzaInfo item) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setObject(1, item.getDtUpdate());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4,item.getSize());
            preparedStatement.setLong(5,id);
            preparedStatement.setObject(6, dtUpdate);

            int countUpdatedRows = preparedStatement.executeUpdate();

            if (countUpdatedRows != 1) {
                if (countUpdatedRows == 0) {
                    throw new IllegalArgumentException("Не смогли обновить какую либо запись");
                } else {
                    throw new IllegalArgumentException("Обновили более одной записи");
                }
            }

            return read(id);
        } catch (SQLException e) {
            throw new RuntimeException("При сохранении данных произошла ошибка", e);
        }
        
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, id);
            preparedStatement.setObject(2, dtUpdate);

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

    public IPizzaInfo readById (long id){

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PIZZAINFO_BY_ID, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, id);   //probably it is being put in WHERE construction

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        return mapper(resultSet);
                    }
                }
            }
         catch (SQLException e) {
            throw new RuntimeException("При чтении данных произошла ошибка", e);
        }
        return null;
    }


    public IPizzaInfo mapper(ResultSet resultSet) throws SQLException {
        return new PizzaInfo(
                resultSet.getLong(1),
                resultSet.getObject(2, LocalDateTime.class),
                resultSet.getObject(3, LocalDateTime.class),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6));

    }
}
