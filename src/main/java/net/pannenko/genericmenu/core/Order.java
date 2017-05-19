package net.pannenko.genericmenu.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

import org.json.JSONObject;

@JsonInclude(Include.NON_NULL)
public class Order {

  @NotNull
  @JsonProperty
  private int id;

  @NotNull
  @JsonProperty
  private Menu menu;

  @NotNull
  @JsonProperty
  private JSONObject data;

  public Order() {
  }

  public Order(int id, Menu menu, JSONObject data) {
    this.id = id;
    this.menu = menu;
    this.data = data;
  }

  public Order(Menu menu, JSONObject data) {
    this.menu = menu;
    this.data = data;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public JSONObject getData() {
    return data;
  }

  public void setData(JSONObject data) {
    this.data = data;
  }

}