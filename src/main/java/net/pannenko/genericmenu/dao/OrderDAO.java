package net.pannenko.genericmenu.dao;

import java.util.List;

import org.json.JSONObject;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import net.pannenko.genericmenu.core.Order;
import net.pannenko.genericmenu.core.mapper.OrderMapper;
import net.pannenko.genericmenu.core.mapper.OrderMenuMapper;


public interface OrderDAO {

  @RegisterMapper(OrderMenuMapper.class)
  @SqlQuery("select O.id, O.data, M.id AS MENU_ID, M.name AS MENU_NAME from ORDERS O JOIN MENUS M ON M.ID = O.MENU_ID")
  List<Order> getAll();

  @RegisterMapper(OrderMenuMapper.class)
  @SqlQuery("select O.id, O.data, M.id AS MENU_ID, M.name AS MENU_NAME from ORDERS O JOIN MENUS M ON M.ID = O.MENU_ID where O.MENU_ID = :menuId")
  List<Order> getMenuOrders(@Bind("menuId") int menuId);

  @RegisterMapper(OrderMapper.class)
  @SqlQuery("select * from ORDERS where ID = :id")
  Order findById(@Bind("id") int id);

  @SqlUpdate("delete from ORDERS where ID = :id")
  int deleteById(@Bind("id") int id);

  @RegisterMapper(OrderMapper.class)
  @SqlQuery("insert into ORDERS (MENU_ID, DATA) values (:menuId, :data) returning *")
  Order insert(@Bind("menuId") int menuId, @BindJson("data") JSONObject data);
  
  @RegisterMapper(OrderMapper.class)
  @SqlQuery("update ORDERS set DATA = :data where ID = :id returning *")
  Order update(@Bind("id") int id, @BindJson("data") JSONObject data);
}
