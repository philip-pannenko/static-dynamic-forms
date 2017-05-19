package net.pannenko.genericmenu.dao;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.SQLException;

import org.json.JSONObject;
import org.postgresql.util.PGobject;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@BindingAnnotation(BindJson.JsonBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface BindJson {
  String value();

  public static class JsonBinderFactory implements BinderFactory {
    @Override
    public Binder build(Annotation annotation) {
      return new Binder<BindJson, JSONObject>() {
        @Override
        public void bind(SQLStatement q, BindJson bind, JSONObject jsonString) {
          try {
            PGobject data = new PGobject();
            data.setType("jsonb");
            data.setValue(jsonString.toString());
            q.bind(bind.value(), data);
          } catch (SQLException ex) {
            throw new IllegalStateException("Error Binding JSON", ex);
          }
        }
      };
    }
  }
}