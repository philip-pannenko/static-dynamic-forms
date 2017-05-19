package net.pannenko.genericmenu.core.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import net.pannenko.genericmenu.core.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements ResultSetMapper<Menu> {
  public Menu map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
    return new Menu(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getString("DOCUMENT"));
  }
}