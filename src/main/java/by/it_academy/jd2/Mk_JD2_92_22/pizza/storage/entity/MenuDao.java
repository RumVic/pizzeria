package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.Menu;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuDao;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements IMenuDao {
    private static final String INSERT_SQL = "INSERT INTO app.menu(\n" +
            "\tdt_create, dt_update, name, enable)\n" +
            "\tVALUES (?, ?, ?, ?);";

    private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, name, enable\n" +
            "\tFROM app.menu\n" +
            "\tWHERE id = ?;";

    private static final String SELECT_SQL = "SELECT id, dt_create, dt_update, name, enable\n" +
            "\tFROM app.menu;";


    private static final String UPDATE_SQL = "UPDATE app.menu\n" +
            "\tSET dt_update = ?, name = ?, enable = ?\n" +
            "\tWHERE id = ? and dt_update = ?;";

    private static final String DELETE_SQL = "DELETE FROM app.menu\n" +
            "\tWHERE id = ? and dt_update = ?;";

    private final DataSource ds;

    public MenuDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public IMenu create(IMenu item) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stm.setObject(1, item.getDtCreate());
            stm.setObject(2, item.getDtUpdate());
            stm.setString(3, item.getName());
            stm.setBoolean(4, item.isEnabled());

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
    public IMenu read(long id) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(SELECT_BY_ID_SQL)
        ) {
            stm.setObject(1, id);//probably it is being put in WHERE construction

            try (ResultSet rs = stm.executeQuery()) {
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
    public List<IMenu> get() {
        List<IMenu> data = new ArrayList<>();
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(SELECT_SQL)
        ) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    data.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("При сохранении данных произошла ошибка", e);
        }

        return data;
    }

    @Override
    public IMenu update(long id, LocalDateTime dtUpdate, IMenu item) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            stm.setObject(1, item.getDtUpdate());
            stm.setString(2, item.getName());
            stm.setBoolean(3, item.isEnabled());
            stm.setLong(4, id);
            stm.setObject(5, dtUpdate);

            int countUpdatedRows = stm.executeUpdate();

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
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            stm.setLong(1, id);
            stm.setObject(2, dtUpdate);

            int countUpdatedRows = stm.executeUpdate();

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

    public IMenu mapper(ResultSet rs) throws SQLException {
        return new Menu(
                rs.getLong(1),
                rs.getObject(2, LocalDateTime.class),
                rs.getObject(3, LocalDateTime.class),
                rs.getString(4),
                rs.getBoolean(5)
        );
    }
}
