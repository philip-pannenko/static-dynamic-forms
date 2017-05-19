package net.pannenko.genericmenu.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.constraints.NotNull;

import org.json.JSONObject;

@JsonInclude(Include.NON_NULL)
public class Menu {

  @NotNull
  @JsonProperty
  private int id;

  @NotNull
  @JsonProperty
  private String name;

  @NotNull
  @JsonProperty
  private String document;

  @NotNull
  @JsonProperty
  private JSONObject menu;

  public Menu() {
  }

  public Menu(int id) {
    this.id = id;
  }

  public Menu(String name) {
    this.name = name;
  }

  public Menu(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Menu(String name, String document) {
    this.name = name;
    this.document = document;
  }

  public Menu(int id, String name, String document) {
    this.id = id;
    this.name = name;
    this.document = document;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public JSONObject getMenu() {
    return menu;
  }

  public void setMenu(JSONObject menu) {
    this.menu = menu;
  }

}