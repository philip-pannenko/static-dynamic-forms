package net.pannenko.genericmenu.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import net.pannenko.genericmenu.core.Menu;
import net.pannenko.genericmenu.core.mapper.MenuMapper;

@RegisterMapper(MenuMapper.class)
public interface MenuDAO {

  @SqlQuery("select * from MENUS")
  List<Menu> getAll();

  @SqlQuery("select * from MENUS where ID = :id")
  Menu findById(@Bind("id") int id);

  @SqlQuery("select * from MENUS where NAME = :name")
  Menu findByName(@Bind("name") String name);

  @SqlUpdate("delete from MENUS where ID = :id")
  int deleteById(@Bind("id") int id);

  @SqlQuery("update MENUS set NAME = :name, DOCUMENT = :document where ID = :id  returning *")
  Menu update(@BindBean Menu menu);

  @SqlQuery("insert into MENUS (NAME, DOCUMENT) values (:name, :document)  returning *")
  Menu insert(@BindBean Menu menu);
}
