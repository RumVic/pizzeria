package by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.entity;

import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IMenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.IOrder;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.api.ISelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.MenuRow;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.Order;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.PizzaInfo;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.core.entity.SelectedItem;
import by.it_academy.jd2.Mk_JD2_92_22.pizza.storage.api.IOrderDao;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {


    private final String INSERT_SQL = "INSERT INTO app.order_general(\n" +
            "\tdt_create, dt_update, state)\n" +
            "\tVALUES (?, ?, ?);";

    private final String SELECT_ORDER_BY_ID = "SELECT \n" +
            "  id, \n" +
            "  dt_create, \n" +
            "  dt_update, \n" +
            "  state \n" +
            "FROM \n" +
            "  app.order_general \n" +
            "WHERE \n" +
            "  id = ?;\n";

    private final String SELECT_BY_ID_SQL = "SELECT \n" +
            "  selected_item.id,\n" +
            "  selected_item.info_client, \n" +
            "  selected_item.count, \n" +
            "  menu_row.price, \n" +
            "  menu_row.menu, \n" +
            "  pizza_info.name \n" +
            "FROM \n" +
            "  app.selected_item\n" +
            "  /*INNER JOIN app.selected_item on selected_item.order_id = order_general.id */\n" +
            "  INNER JOIN app.menu_row on selected_item.selected_positions = menu_row.id \n" +
            "  INNER JOIN app.pizza_info on menu_row.info = pizza_info.id \n" +
            "WHERE \n" +
            "  selected_item.order_id = ?;\n";

    private static final String INSERT_SQL_TO_SELECTED_ITEM = "INSERT INTO app.selected_item(\n" +
            "\tdt_update, info_client, selected_positions, count, order_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?);";

    //id,dt_create,dt_update,state,price,menu,info_client,count,name


    public final DataSource dataSource;

    public OrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public IOrder create(IOrder item) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementSelectItem = connection.prepareStatement(INSERT_SQL_TO_SELECTED_ITEM, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setObject(1, item.getDtCreate());
            preparedStatement.setObject(2, item.getDtUpdate());
            preparedStatement.setLong(3, item.getState());

            int updated = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();) {
                while (generatedKeys.next()) {

                    long orderId = generatedKeys.getLong(1);

                    for (ISelectedItem selectedItem : item.getSelected()) {
                        preparedStatementSelectItem.setObject(1, selectedItem.getDtUpdate());
                        preparedStatementSelectItem.setString(2, selectedItem.getInfoClient());
                        preparedStatementSelectItem.setLong(3, selectedItem.getSelectedPositions());
                        preparedStatementSelectItem.setLong(4, selectedItem.getCount());
                        preparedStatementSelectItem.setLong(5, selectedItem.getOrder());

                        preparedStatementSelectItem.addBatch();
                    }
                    preparedStatementSelectItem.executeBatch();
                    return read(orderId);
                }
                return null;
            }

        } catch (SQLException sql) {
            throw new RuntimeException("There is error during saving data", sql);
        }
    }

    @Override
    public IOrder read(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatementOrder = connection.prepareStatement(SELECT_ORDER_BY_ID, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementSelectedItem = connection.prepareStatement(SELECT_BY_ID_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatementOrder.setLong(1, id);

            try (ResultSet resultSetOrder = preparedStatementOrder.executeQuery()) {
                while (resultSetOrder.next()) {
                    preparedStatementSelectedItem.setLong(1, id);
                    try (ResultSet resultSetSelItem = preparedStatementSelectedItem.executeQuery()) {
                        return mapper(resultSetOrder,resultSetSelItem);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("При чтении данных произошла ошибка", e);
        }
        return null;
    }


    @Override
    public List<IOrder> get() {
        return null;
    }

    @Override
    public IOrder update(long id, LocalDateTime dtUpdate, IOrder item) {
        return null;
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) {

    }

    public IOrder mapper(ResultSet resultSetOrder, ResultSet resultSetSelItem) throws SQLException {
        Order order = new Order(
                resultSetOrder.getLong(1),
                resultSetOrder.getObject(2, LocalDateTime.class),
                resultSetOrder.getObject(3, LocalDateTime.class),
                resultSetOrder.getLong(4));

        List<ISelectedItem> listSelectedItem = new ArrayList<>();
        List<IMenuRow> listMenuRow = new ArrayList<>();

        while (resultSetSelItem.next()) {

            SelectedItem selectedItem = new SelectedItem(
                    resultSetSelItem.getLong("id"),
                    resultSetSelItem.getString("info_client"),
                    resultSetSelItem.getLong("count"));
            listSelectedItem.add(selectedItem);

            MenuRow menuRow = new MenuRow(resultSetOrder.getDouble("price"));
            listMenuRow.add(menuRow);

            PizzaInfo pizzaInfo = new PizzaInfo(resultSetSelItem.getString("name"));
            selectedItem.setMenuRowPosition(menuRow);
        }
        order.setListSelectedItem(listSelectedItem);
        return order;
    }
}
