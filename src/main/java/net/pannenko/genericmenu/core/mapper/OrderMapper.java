package net.pannenko.genericmenu.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import net.pannenko.genericmenu.core.Menu;
import net.pannenko.genericmenu.core.Order;

public class OrderMapper implements ResultSetMapper<Order> {
  public Order map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {

    String data = resultSet.getString("DATA");

    if (data == null) {
      return new Order(resultSet.getInt("ID"), new Menu(resultSet.getInt("MENU_ID")), new JSONObject(JSONObject.NULL));
    } else {
      try {
        return new Order(resultSet.getInt("ID"), new Menu(resultSet.getInt("MENU_ID")), new JSONObject(data));
      } catch (JSONException e) {
        return new Order(resultSet.getInt("ID"), new Menu(resultSet.getInt("MENU_ID")), new JSONObject(JSONObject.NULL));
      }
    }
  }
}