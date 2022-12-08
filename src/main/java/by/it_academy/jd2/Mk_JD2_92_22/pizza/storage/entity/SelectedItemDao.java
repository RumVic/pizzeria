package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.SelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IMenuRowDao;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.ISelectedItemDao;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SelectedItemDao implements ISelectedItemDao {

    private final DataSource dataSource;

    private IMenuRowDao menuRowDao;


    public SelectedItemDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    // SQL for create
    private static final String INSERT_SQL = "INSERT INTO app.selected_item(\n" +
            "\tdt_create, dt_update, info_client, selected_positions, count, order_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?);";

    // SQL for getting
    private  static final String SELECT_SQL =  "SELECT id, dt_create, dt_update, info_client, selected_positions, count, order_id\n" +
            "\tFROM app.selected_item;";


    private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, info_client, selected_positions, count, order_id\n" +
            "\tFROM app.selected_item\n" +
            "\tWHERE id = ?;";

    // SQL for Updated
    private static final String UPDATE_SQL = "UPDATE app.selected_item\n" +
            "\tSET dt_update = ?, info_client = ?, selected_positions = ?, count = ?, order_id = ?\n" +
            "\tWHERE id = ? and dt_update = ?;";

    //SQL for deleting
    private static final String DELETE_SQL = "DELETE FROM app.selected_item\n" +
            "\tWHERE id = ? and dt_update = ?;";


    @Override
    public ISelectedItem create(ISelectedItem item) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setObject(1,item.getDtCreate());
            preparedStatement.setObject(2,item.getDtUpdate());
            preparedStatement.setString(3,item.getInfoClient());
            preparedStatement.setLong(4,item.getSelectedPositions());
            preparedStatement.setLong(5,item.getCount());
            preparedStatement.setLong(6,item.getOrder());

            int updated = preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return read(generatedKeys.getLong(1));
            }
            return read(generatedKeys.getLong(1));

        }catch (SQLException e) {
            throw new RuntimeException("failed to create line");
        }
    }

    @Override
    public ISelectedItem read(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID_SQL,Statement.RETURN_GENERATED_KEYS))
        {
            stm.setObject(1, id);//probably it is being put in WHERE construction

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("There is error during reading data", e);
        }
        return null;
    }

    @Override
    public List<ISelectedItem> get() {
        List<ISelectedItem> data = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)
        ) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    data.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("There is error during reading data", e);
        }
        return data;
    }

    @Override
    public ISelectedItem update(long id, LocalDateTime dtUpdate, ISelectedItem item) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stm = connection.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            stm.setObject(1, item.getDtUpdate());
            stm.setString(2, item.getInfoClient());
            stm.setLong(3, item.getSelectedPositions());
            stm.setLong(4,item.getCount());
            stm.setLong(5,item.getOrder());
            stm.setLong(6, id);
            stm.setObject(7, dtUpdate);

            int countUpdatedRows = stm.executeUpdate();

            if (countUpdatedRows != 1) {
                if (countUpdatedRows == 0) {
                    throw new IllegalArgumentException("We can't update some entry");
                } else {
                    throw new IllegalArgumentException("There are several entries updated");
                }
            }

            return read(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error while data was being saving", e);
        }
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stm = connection.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            stm.setLong(1, id);
            stm.setObject(2, dtUpdate);

            int countUpdatedRows = stm.executeUpdate();

            if (countUpdatedRows != 1) {
                if (countUpdatedRows == 0) {
                    throw new IllegalArgumentException("We can't update some entry");
                } else {
                    throw new IllegalArgumentException("Was deleted more than one line ");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while data was being saving", e);
        }

    }


    public ISelectedItem mapper(ResultSet rs) throws SQLException {
        return new SelectedItem(
                rs.getLong(1),
                rs.getObject(2, LocalDateTime.class),
                rs.getObject(3, LocalDateTime.class),
                rs.getString(4),
                rs.getLong(5),
                rs.getLong(6),
                rs.getLong(7));
                //menuRowDao.read(rs.getLong(5)));
    }

}
